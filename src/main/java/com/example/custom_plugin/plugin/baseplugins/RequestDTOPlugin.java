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

public class RequestDTOPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public RequestDTOPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public RequestDTOPlugin() {
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
    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "RequestDTO");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);
    topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
    topLevelClass.addAnnotation("@Data");

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated BaseService for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");

    topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.regex.Pattern"));

    Field serialVersionUID = new Field("serialVersionUID=1L", new FullyQualifiedJavaType("long"));
    serialVersionUID.setVisibility(JavaVisibility.PRIVATE);
    serialVersionUID.setFinal(true);
    serialVersionUID.setStatic(true);
    serialVersionUID.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(serialVersionUID);

    Field username = new Field("username", new FullyQualifiedJavaType("java.lang.String"));
    username.setVisibility(JavaVisibility.PRIVATE);
    username.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(username);

    Field password = new Field("password", new FullyQualifiedJavaType("java.lang.String"));
    password.setVisibility(JavaVisibility.PRIVATE);
    password.addJavaDocLine("/** This is an example. */");
    topLevelClass.addField(password);

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
