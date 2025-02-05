package com.example.custom_plugin.plugin.baseplugins;

import java.text.Annotation;
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
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TypeParameter;

import com.jayway.jsonpath.internal.function.Parameter;

public class AuthCheckAnnotationPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public AuthCheckAnnotationPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public AuthCheckAnnotationPlugin() {
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

    // modelClassName;
    Interface annotation = new Interface(packageName + "." + "AuthCheck");
    annotation.setVisibility(JavaVisibility.PUBLIC);
    //annotation.addTypeParameter(new TypeParameter ("@"));

    // Add class documentation
    annotation.addJavaDocLine("/**");
    annotation.addJavaDocLine(" * This is a generated BaseService for demonstration purposes.");
    annotation.addJavaDocLine(" */");

    annotation.addImportedType(new FullyQualifiedJavaType("java.lang.annotation.ElementType"));
    annotation.addImportedType(new FullyQualifiedJavaType("java.lang.annotation.Retention"));
    annotation.addImportedType(new FullyQualifiedJavaType("java.lang.annotation.RetentionPolicy"));
    annotation.addImportedType(new FullyQualifiedJavaType("java.lang.annotation.Target"));
    annotation.addImportedType(new FullyQualifiedJavaType(packageName + "." + "UserRoleEnum"));

    annotation.addAnnotation("@Target(ElementType.METHOD)");
    annotation.addAnnotation("@Retention(RetentionPolicy.RUNTIME)");

    Field mustRole = new Field ("mustRole()", new FullyQualifiedJavaType(packageName + "." + "UserRoleEnum"));
    mustRole.addJavaDocLine("/** This is an example modelAssembler. */");
    annotation.addField(mustRole);

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(annotation, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
