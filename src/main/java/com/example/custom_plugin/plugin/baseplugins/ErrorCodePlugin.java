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

public class ErrorCodePlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public ErrorCodePlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public ErrorCodePlugin() {
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
    // String modelClassName = introspectedTable.getBaseRecordType();

    // String[] strList = modelClassName.split("\\.");
    // String _modelName = Arrays.asList(strList).get(strList.length - 1);
    // System.out.println(packageName);
    // System.out.println(_modelName);
    // String interfaceName =
    // introspectedTable.getFullyQualifiedTable().getDomainObjectName() +
    // modelClassName;
    TopLevelEnumeration topLevelEnumeration = new TopLevelEnumeration(packageName + "." + "ErrorCode");
    topLevelEnumeration.setVisibility(JavaVisibility.PUBLIC);

    // Add class documentation
    topLevelEnumeration.addJavaDocLine("/**");
    topLevelEnumeration.addJavaDocLine(" * This is a generated ErrorCode for demonstration purposes.");
    topLevelEnumeration.addJavaDocLine(" */");

    // Add a private field
    topLevelEnumeration.addField(new Field(
        "SUCCESS(0, \"ok\"), PARAMS_ERROR(40000, \"请求参数错误\"),  NOT_LOGIN_ERROR(40100, \"未登录\"), NO_AUTH_ERROR(40101, \"无权限\"), NOT_FOUND_ERROR(40400, \"请求数据不存在\"), FORBIDDEN_ERROR(40300, \"禁止访问\"), SYSTEM_ERROR(50000, \"系统内部异常\"), OPERATION_ERROR(50001, \"操作失败\")",
        new FullyQualifiedJavaType("")));

    // Add a private field
    var _statusCode = new Field("statusCode", new FullyQualifiedJavaType("java.lang.String"));
    _statusCode.setVisibility(JavaVisibility.PRIVATE);
    _statusCode.addJavaDocLine("/** This is an example ErrorCode. */");
    topLevelEnumeration.addField(_statusCode);

    var _statusCodeValue = new Field("statusCodeValue", new FullyQualifiedJavaType("java.lang.Integer"));
    _statusCodeValue.setVisibility(JavaVisibility.PRIVATE);
    _statusCodeValue.addJavaDocLine("/** This is an example ErrorCode. */");
    topLevelEnumeration.addField(_statusCodeValue);

    Method _defaultconstructor = new Method("ErrorCode");
    _defaultconstructor.setConstructor(true);
    // _defaultconstructor.setVisibility(JavaVisibility.PUBLIC);
    topLevelEnumeration.addMethod(_defaultconstructor);

    Method _constructor = new Method("ErrorCode");
    _constructor.setConstructor(true);
    // _constructor.setVisibility(JavaVisibility.PUBLIC);
    _constructor.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "statusCodeValue"));
    Parameter parameter = new Parameter(new FullyQualifiedJavaType("java.lang.String"), "statusCode");
    _constructor.addParameter(parameter);
    _constructor.addBodyLine("this.statusCodeValue = statusCodeValue;");
    _constructor.addBodyLine("this.statusCode = statusCode;");
    topLevelEnumeration.addMethod(_constructor);

    Method getStatusCode = new Method("getStatusCode");
    //getStatusCode.setVisibility(JavaVisibility.PUBLIC);
    getStatusCode.addBodyLine("return statusCode;");
    getStatusCode.setReturnType(new FullyQualifiedJavaType("java.lang.String"));
    topLevelEnumeration.addMethod(getStatusCode);

    Method getStatusCodeValue = new Method("getStatusCodeValue");
    //getStatusCodeValue.setVisibility(JavaVisibility.PUBLIC);
    getStatusCodeValue.addBodyLine("return statusCodeValue;");
    getStatusCodeValue.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
    topLevelEnumeration.addMethod(getStatusCodeValue);

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelEnumeration, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
