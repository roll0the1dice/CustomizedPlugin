package com.example.custom_plugin;

import org.hibernate.annotations.IdGeneratorType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// public class MyCustomIdGenerator implements IdentifierGenerator {

//   @Override
//   public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//       // 例如：生成一个随机的正 Long 值（注意：实际使用时请确保生成逻辑能保证全局唯一）
//       long randomId = ThreadLocalRandom.current().nextLong();
//       return randomId < 0 ? -randomId : randomId;
//   }
// }

@IdGeneratorType(SnowflakeIdGenerator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface CustomId {
    // 可根据需要定义属性，比如初始值、随机种子等
    long workerId() default 10;
    long datacenterId() default 10;
}
