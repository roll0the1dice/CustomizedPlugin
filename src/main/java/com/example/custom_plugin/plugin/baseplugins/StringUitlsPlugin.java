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

public class StringUitlsPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public StringUitlsPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public StringUitlsPlugin() {
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
    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "StringUitls");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated BaseService for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");

    //topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.regex.Pattern"));

    Field field = new Field("SPECIAL_CHAR_REGEX=\"^[a-zA-Z0-9]*$\"", new FullyQualifiedJavaType("java.lang.String"));
    field.setVisibility(JavaVisibility.PUBLIC);
    field.setFinal(true);
    field.setStatic(true);
    field.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(field);


    Method _defaultconstructor = new Method("StringUitls");
    _defaultconstructor.setConstructor(true);
    _defaultconstructor.setVisibility(JavaVisibility.PUBLIC);
    topLevelClass.addMethod(_defaultconstructor);

    Method _hasSpecialCharacters = new Method("hasSpecialCharacters");
    _hasSpecialCharacters.setVisibility(JavaVisibility.PUBLIC);
    _hasSpecialCharacters.setStatic(true);
    _hasSpecialCharacters.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), "input"));
    _hasSpecialCharacters.setReturnType(new FullyQualifiedJavaType("java.lang.Boolean"));
    _hasSpecialCharacters.addBodyLine(" return !Pattern.matches(SPECIAL_CHAR_REGEX, input);");
    topLevelClass.addMethod(_hasSpecialCharacters);

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
