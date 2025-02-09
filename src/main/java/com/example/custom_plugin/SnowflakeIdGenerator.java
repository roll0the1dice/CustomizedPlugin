package com.example.custom_plugin;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.yaml.snakeyaml.events.Event.ID;

import com.example.custom_plugin.model.Users;

public class SnowflakeIdGenerator implements IdentifierGenerator {
  private long twepoch = 1672531200000L; // 起始时间戳 (2023-01-01)
  private long workerIdBits = 5L; // 机器 ID 位数
  private long datacenterIdBits = 5L; // 数据中心 ID 位数
  private long sequenceBits = 12L; // 序列号位数

  private long maxWorkerId = (1L << workerIdBits) - 1;
  private long maxDatacenterId = (1L << datacenterIdBits) - 1;
  private long sequenceMask = (1L << sequenceBits) - 1;

  private long workerIdShift = sequenceBits;
  private long datacenterIdShift = sequenceBits + workerIdBits;
  private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

  private long workerId;
  private long datacenterId;
  private long sequence = 0L;
  private long lastTimestamp = -1L;

  public SnowflakeIdGenerator() {
    twepoch = 1672531200000L;
    workerIdBits = 5L; 
    datacenterIdBits = 5L; 
    sequenceBits = 12L; 

    maxWorkerId = (1L << workerIdBits) - 1;
    maxDatacenterId = (1L << datacenterIdBits) - 1;
    sequenceMask = (1L << sequenceBits) - 1;

    workerIdShift = sequenceBits;
    datacenterIdShift = sequenceBits + workerIdBits;
    timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    sequence = 0L;
    lastTimestamp = -1L;

    Class<Users> userClass = Users.class;

    Field[] fields = userClass.getDeclaredFields();
    try {
      for (Field __field : fields) {
        if (__field.isAnnotationPresent(jakarta.persistence.Id.class)) {
          this.workerId = __field.getAnnotation(CustomId.class).workerId();
          this.datacenterId = __field.getAnnotation(CustomId.class).datacenterId();
          break;
        }
      }
    } catch (SecurityException e) {
      e.printStackTrace();
    }

    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException("Worker ID out of range");
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      throw new IllegalArgumentException("Datacenter ID out of range");
    }
  }

  public synchronized long nextId() {
    long timestamp = System.currentTimeMillis();

    if (timestamp < lastTimestamp) {
      throw new RuntimeException("Clock moved backwards. Refusing to generate ID");
    }

    if (timestamp == lastTimestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        while ((timestamp = System.currentTimeMillis()) <= lastTimestamp) {
          // 等待下一毫秒
        }
      }
    } else {
      sequence = 0;
    }

    lastTimestamp = timestamp;
    return ((timestamp - twepoch) << timestampLeftShift)
        | (datacenterId << datacenterIdShift)
        | (workerId << workerIdShift)
        | sequence;

  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
    return nextId();
  }

  // @Override
  // public void configure(Type type, Properties parameters, ServiceRegistry
  // serviceRegistry) throws MappingException {
  // System.out.println(parameters.getProperty("workerId"));
  // System.out.println(parameters.getProperty("datacenterId"));
  // System.out.println(parameters.toString());

  // String _workerId = parameters.getProperty("workerId", "1");
  // String _datacenterId = parameters.getProperty("datacenterId", "1");
  // try {
  // this.workerId = _workerId != null ? Integer.parseInt(_workerId) : 1;
  // this.datacenterId = _datacenterId != null ? Integer.parseInt(_datacenterId) :
  // 1;
  // } catch (NumberFormatException e) {
  // throw new MappingException("Invalid incrementSize value: " + _workerId + ", "
  // + _datacenterId, e);
  // }
  // if (workerId > maxWorkerId || workerId < 0) {
  // throw new IllegalArgumentException("Worker ID out of range");
  // }
  // if (datacenterId > maxDatacenterId || datacenterId < 0) {
  // throw new IllegalArgumentException("Datacenter ID out of range");
  // }
  // }

  public static void main(String[] args) {
    // SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1, 1);
    // System.out.println(generator.nextId());
  }
}
