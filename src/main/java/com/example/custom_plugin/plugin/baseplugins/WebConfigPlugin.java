package com.example.custom_plugin.plugin.baseplugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class WebConfigPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public WebConfigPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public WebConfigPlugin() {
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
    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "WebConfig");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated BaseService for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");

    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.data.web.config.EnableSpringDataWebSupport"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.servlet.config.annotation.WebMvcConfigurer"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.context.annotation.Configuration"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.context.annotation.EnableAspectJAutoProxy"));

    topLevelClass.addAnnotation("@Configuration");
    topLevelClass.addAnnotation("@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)");
    topLevelClass.addAnnotation("@EnableAspectJAutoProxy");

    topLevelClass.addSuperInterface(new FullyQualifiedJavaType("org.springframework.web.servlet.config.annotation.WebMvcConfigurer"));

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
