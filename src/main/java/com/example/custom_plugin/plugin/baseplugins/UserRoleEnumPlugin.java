package com.example.custom_plugin.plugin.baseplugins;

import java.util.ArrayList;
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
import org.mybatis.generator.api.dom.java.TopLevelEnumeration;

public class UserRoleEnumPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public UserRoleEnumPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public UserRoleEnumPlugin() {
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
    //String modelClassName = introspectedTable.getBaseRecordType();

    //String[] strList = modelClassName.split("\\.");
    //String _modelName = Arrays.asList(strList).get(strList.length - 1);
    // System.out.println(packageName);
    // System.out.println(_modelName);
    // String interfaceName =
    // introspectedTable.getFullyQualifiedTable().getDomainObjectName() +
    // modelClassName;
    TopLevelEnumeration topLevelEnumeration = new TopLevelEnumeration(packageName + "." + "UserRoleEnum");
    topLevelEnumeration.setVisibility(JavaVisibility.PUBLIC);

    // Add class documentation
    topLevelEnumeration.addJavaDocLine("/**");
    topLevelEnumeration.addJavaDocLine(" * This is a generated UserRoleEnum for demonstration purposes.");
    topLevelEnumeration.addJavaDocLine(" */");

    // Add a private field
    topLevelEnumeration.addField(new Field("USER(\"user\", 0), ADMIN(\"admin\", 1), BAN(\"baned user\", 2)", new FullyQualifiedJavaType("")));
    //topLevelEnumeration.addField(new Field("ADMIN(\"admin\")", new FullyQualifiedJavaType("")));
    //topLevelEnumeration.addField(new Field("BAN(\"baned user\")", new FullyQualifiedJavaType("")));

    // Add a private field
    var _description = new Field ("description", new FullyQualifiedJavaType("java.lang.String"));
    _description.setVisibility(JavaVisibility.PRIVATE);
    _description.addJavaDocLine("/** This is an example UserRoleEnum. */");
    topLevelEnumeration.addField(_description);

    var _value = new Field ("value", new FullyQualifiedJavaType("java.lang.Integer"));
    _value.setVisibility(JavaVisibility.PRIVATE);
    _value.addJavaDocLine("/** This is an example UserRoleEnum. */");
    topLevelEnumeration.addField(_value);

    Method _defaultconstructor = new Method("UserRoleEnum");
    _defaultconstructor.setConstructor(true);
    //_defaultconstructor.setVisibility(JavaVisibility.PUBLIC);
    topLevelEnumeration.addMethod(_defaultconstructor);

  
    Method _constructor = new Method("UserRoleEnum");
    _constructor.setConstructor(true);
    //_constructor.setVisibility(JavaVisibility.PUBLIC);
    Parameter parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.String"), "description");
    _constructor.addParameter(parameter);
    _constructor.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "value"));
    _constructor.addBodyLine("this.description = description;");
    _constructor.addBodyLine("this.value = value;");
    topLevelEnumeration.addMethod(_constructor);

    Method _getDescription= new Method("getDescription");
    //_getDescription.setVisibility(JavaVisibility.PUBLIC);
    _getDescription.addBodyLine("return description;");
    _getDescription.setReturnType(new FullyQualifiedJavaType("java.lang.String"));
    topLevelEnumeration.addMethod(_getDescription);

    Method _getValue = new Method("getValue");
    //_getDescription.setVisibility(JavaVisibility.PUBLIC);
    _getValue.addBodyLine("return value;");
    _getValue.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
    topLevelEnumeration.addMethod(_getValue);
  
   // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelEnumeration, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
