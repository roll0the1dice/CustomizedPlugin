package com.example.custom_plugin.plugin.baseplugins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class SnowflakeIdGeneratorImplPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public SnowflakeIdGeneratorImplPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public SnowflakeIdGeneratorImplPlugin() {
    super();
  }

  @Override
  public boolean validate(List<String> warnings) {
    // 在此验证插件的配置是否合法，返回true表示插件配置有效
    String myCustomParameter = properties.getProperty("targetPackage");
    if (myCustomParameter != null)
      this.targetPackage = myCustomParameter;
    else
      return false;
    return true;
  }

  @Override
  public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {

    return true;
  }

  @Override
  public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
    // List to store additional generated files
    List<GeneratedJavaFile> additionalFiles = new ArrayList<>();

    // 设置接口类的包名
    String packageName = this.targetPackage;

    // 创建接口类（例如 UserMapper）
    String modelClassName = introspectedTable.getBaseRecordType();

    String[] strList = modelClassName.split("\\.");
    String _modelName = Arrays.asList(strList).get(strList.length - 1);
    System.out.println(packageName);
    System.out.println(_modelName);
    // String interfaceName =
    // introspectedTable.getFullyQualifiedTable().getDomainObjectName() +
    // modelClassName;
    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "SnowflakeIdGeneratorImpl");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);
    topLevelClass.addSuperInterface(new FullyQualifiedJavaType("org.hibernate.id.IdentifierGenerator"));

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated SnowflakeIdGeneratorImplPlugin for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");

    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.lang.reflect.Field"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.hibernate.HibernateException"));
    topLevelClass
        .addImportedType(new FullyQualifiedJavaType("org.hibernate.engine.spi.SharedSessionContractImplementor"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.hibernate.id.IdentifierGenerator"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType(modelClassName));
    topLevelClass.addImportedType(new FullyQualifiedJavaType(packageName + "." + "SnowflakeIdGenerator"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));

    Field twepoch = new Field("twepoch", new FullyQualifiedJavaType("java.lang.Long"));
    twepoch.setVisibility(JavaVisibility.PRIVATE);
    twepoch.setFinal(false);
    twepoch.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(twepoch);

    Field workerIdBits = new Field("workerIdBits", new FullyQualifiedJavaType("java.lang.Long"));
    workerIdBits.setVisibility(JavaVisibility.PRIVATE);
    workerIdBits.setFinal(false);
    workerIdBits.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(workerIdBits);

    Field datacenterIdBits = new Field("datacenterIdBits", new FullyQualifiedJavaType("java.lang.Long"));
    datacenterIdBits.setVisibility(JavaVisibility.PRIVATE);
    datacenterIdBits.setFinal(false);
    datacenterIdBits.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(datacenterIdBits);

    Field sequenceBits = new Field("sequenceBits", new FullyQualifiedJavaType("java.lang.Long"));
    sequenceBits.setVisibility(JavaVisibility.PRIVATE);
    sequenceBits.setFinal(false);
    sequenceBits.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(sequenceBits);

    Field maxWorkerId = new Field("maxWorkerId", new FullyQualifiedJavaType("java.lang.Long"));
    maxWorkerId.setVisibility(JavaVisibility.PRIVATE);
    maxWorkerId.setFinal(false);
    maxWorkerId.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(maxWorkerId);

    Field maxDatacenterId = new Field("maxDatacenterId", new FullyQualifiedJavaType("java.lang.Long"));
    maxDatacenterId.setVisibility(JavaVisibility.PRIVATE);
    maxDatacenterId.setFinal(false);
    maxDatacenterId.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(maxDatacenterId);

    Field sequenceMask = new Field("sequenceMask", new FullyQualifiedJavaType("java.lang.Long"));
    sequenceMask.setVisibility(JavaVisibility.PRIVATE);
    sequenceMask.setFinal(false);
    sequenceMask.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(sequenceMask);

    Field workerIdShift = new Field("workerIdShift", new FullyQualifiedJavaType("java.lang.Long"));
    workerIdShift.setVisibility(JavaVisibility.PRIVATE);
    workerIdShift.setFinal(false);
    workerIdShift.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(workerIdShift);

    Field datacenterIdShift = new Field("datacenterIdShift", new FullyQualifiedJavaType("java.lang.Long"));
    datacenterIdShift.setVisibility(JavaVisibility.PRIVATE);
    datacenterIdShift.setFinal(false);
    datacenterIdShift.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(datacenterIdShift);

    Field timestampLeftShift = new Field("timestampLeftShift", new FullyQualifiedJavaType("java.lang.Long"));
    timestampLeftShift.setVisibility(JavaVisibility.PRIVATE);
    timestampLeftShift.setFinal(false);
    timestampLeftShift.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(timestampLeftShift);

    Field workerId = new Field("workerId", new FullyQualifiedJavaType("java.lang.Long"));
    workerId.setVisibility(JavaVisibility.PRIVATE);
    workerId.setFinal(false);
    workerId.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(workerId);

    Field datacenterId = new Field("datacenterId", new FullyQualifiedJavaType("java.lang.Long"));
    datacenterId.setVisibility(JavaVisibility.PRIVATE);
    datacenterId.setFinal(false);
    datacenterId.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(datacenterId);

    Field sequence = new Field("sequence", new FullyQualifiedJavaType("java.lang.Long"));
    sequence.setVisibility(JavaVisibility.PRIVATE);
    sequence.setFinal(false);
    sequence.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(sequence);

    Field lastTimestamp = new Field("lastTimestamp", new FullyQualifiedJavaType("java.lang.Long"));
    lastTimestamp.setVisibility(JavaVisibility.PRIVATE);
    lastTimestamp.setFinal(false);
    lastTimestamp.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(lastTimestamp);

    Method _defaultconstructor = new Method("SnowflakeIdGeneratorImpl");
    _defaultconstructor.setVisibility(JavaVisibility.PUBLIC);
    _defaultconstructor.setConstructor(true);
    String[] _tmpParameters = {
        "twepoch = 1672531200000L;",
        "workerIdBits = 4L;",
        "datacenterIdBits = 2L;",
        "sequenceBits = 12L;",
        "",
        "maxWorkerId = (1L << workerIdBits) - 1;",
        "maxDatacenterId = (1L << datacenterIdBits) - 1;",
        "sequenceMask = (1L << sequenceBits) - 1;",
        "",
        "workerIdShift = sequenceBits;",
        "datacenterIdShift = sequenceBits + workerIdBits;",
        "timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;",
        "",
        "sequence = 0L;",
        "lastTimestamp = -1L;",
        "",
        "Class<Users> userClass = Users.class;",
        "",
        "Field[] fields = userClass.getDeclaredFields();",
        "try {",
        "for (Field __field : fields) {",
        "if (__field.isAnnotationPresent(jakarta.persistence.Id.class)) {",
        "this.workerId = __field.getAnnotation(SnowflakeIdGenerator.class).workerId();",
        "this.datacenterId = __field.getAnnotation(SnowflakeIdGenerator.class).datacenterId();",
        "break;",
        "}",
        "}",
        "} catch (SecurityException e) {",
        "e.printStackTrace();",
        "}",
        "",
        "if (workerId > maxWorkerId || workerId < 0) {",
        "throw new IllegalArgumentException(\"Worker ID out of range\");",
        "}",
        "if (datacenterId > maxDatacenterId || datacenterId < 0) {",
        "throw new IllegalArgumentException(\"Datacenter ID out of range\");",
        "}" };
    List<String> _tmpstringList = Arrays.asList(_tmpParameters);
    Collection<String> _tmpstringCollection = _tmpstringList;
    _defaultconstructor.addBodyLines(_tmpstringCollection);
    topLevelClass.addMethod(_defaultconstructor);

    Method nextId = new Method("nextId");
    nextId.setVisibility(JavaVisibility.PUBLIC);
    nextId.setSynchronized(true);
    nextId.setReturnType(new FullyQualifiedJavaType("java.lang.Long"));
    String[] _tmpParameters2 = {
      "long timestamp = System.currentTimeMillis();",
      "",
      "if (timestamp < lastTimestamp) {",
        "throw new RuntimeException(\"Clock moved backwards. Refusing to generate ID\");",
      "}",
      "",
      "if (timestamp == lastTimestamp) {",
        "sequence = (sequence + 1) & sequenceMask;",
        "if (sequence == 0) {",
          "while ((timestamp = System.currentTimeMillis()) <= lastTimestamp) {",
            "",
          "}",
        "}",
      "} else {",
        "sequence = 0L;",
      "}",
      "",
      "lastTimestamp = timestamp;",
      "return ((timestamp - twepoch) << timestampLeftShift)",
          "| (datacenterId << datacenterIdShift)",
          "| (workerId << workerIdShift)",
          "| sequence;",
    };
    List<String> _tmpstringList2 = Arrays.asList(_tmpParameters2);
    Collection<String> _tmpstringCollection2 = _tmpstringList2;
    nextId.addBodyLines(_tmpstringCollection2);
    topLevelClass.addMethod(nextId);

    Method generate = new Method("generate");
    generate.setVisibility(JavaVisibility.PUBLIC);
    generate.addAnnotation("@Override");
    Parameter session = new Parameter(new FullyQualifiedJavaType("org.hibernate.engine.spi.SharedSessionContractImplementor"), "session");
    Parameter object = new Parameter(new FullyQualifiedJavaType("java.lang.Object"), "object");
    generate.addParameter(session);
    generate.addParameter(object);
    generate.setReturnType(new FullyQualifiedJavaType("java.io.Serializable"));
    generate.addBodyLine("return nextId();");
    topLevelClass.addMethod(generate);


    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
