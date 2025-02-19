package com.example.custom_plugin.plugin.baseplugins;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.mybatis.generator.api.dom.java.TypeParameter;

public class ThrowUtilsPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public ThrowUtilsPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public ThrowUtilsPlugin() {
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
    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "ThrowUtils");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated BaseService for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");


    Method _throwIf = new Method("throwIf");
    _throwIf.setVisibility(JavaVisibility.PUBLIC);
    _throwIf.setStatic(true);
    _throwIf.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Boolean"), "condition"));
    _throwIf.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.RuntimeException"), "runtimeException"));
    _throwIf.addBodyLine("if (condition) throw runtimeException;");
    topLevelClass.addMethod(_throwIf);

    Method _throwIf2 = new Method("throwIf");
    _throwIf2.setVisibility(JavaVisibility.PUBLIC);
    _throwIf2.setStatic(true);
    _throwIf2.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Boolean"), "condition"));
    _throwIf2.addParameter(new Parameter(new FullyQualifiedJavaType("ErrorCode"), "errorCode"));
    _throwIf2.addBodyLine("throwIf(condition, new BusinessException(errorCode.toString()));");
    topLevelClass.addMethod(_throwIf2);

    Method _throwIf3 = new Method("throwIf");
    _throwIf3.setVisibility(JavaVisibility.PUBLIC);
    _throwIf3.setStatic(true);
    _throwIf3.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Boolean"), "condition"));
    _throwIf3.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "code"));
    _throwIf3.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), "message"));
    _throwIf3.addBodyLine("throwIf(condition, new BusinessException(code, message));");
    topLevelClass.addMethod(_throwIf3);

    // Method _assert = new Method("assert");
    // _assert.setVisibility(JavaVisibility.PUBLIC);
    // _assert.setStatic(true);
    // _assert.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Boolean"), "condition"));
    // _assert.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.RuntimeException"), "runtimeException"));
    // _assert.addBodyLine("if (!condition) throw runtimeException;");
    // topLevelClass.addMethod(_assert);

    // Method _assert2 = new Method("assert");
    // _assert2.setVisibility(JavaVisibility.PUBLIC);
    // _assert2.setStatic(true);
    // _assert2.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Boolean"), "condition"));
    // _assert2.addParameter(new Parameter(new FullyQualifiedJavaType("ErrorCode"), "errorCode"));
    // _assert2.addBodyLine("assert(condition, new BusinessException(errorCode.toString()));");
    // topLevelClass.addMethod(_assert2);

    // Method _assert3 = new Method("assert");
    // _assert3.setVisibility(JavaVisibility.PUBLIC);
    // _assert3.setStatic(true);
    // _assert3.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Boolean"), "condition"));
    // _assert3.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "code"));
    // _assert3.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), "message"));
    // _assert3.addBodyLine("assert(condition, new BusinessException(code, message));");
    // topLevelClass.addMethod(_assert3);


    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
