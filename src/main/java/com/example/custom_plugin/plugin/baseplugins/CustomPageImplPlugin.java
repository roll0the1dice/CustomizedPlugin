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
import org.mybatis.generator.api.dom.kotlin.FullyQualifiedKotlinType;

public class CustomPageImplPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public CustomPageImplPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public CustomPageImplPlugin() {
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
    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "CustomPageImpl");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated BaseService for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");

    topLevelClass.addImportedType(new FullyQualifiedJavaType("com.fasterxml.jackson.databind.JsonNode"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("com.fasterxml.jackson.annotation.JsonProperty"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("com.fasterxml.jackson.annotation.JsonIgnoreProperties"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("com.fasterxml.jackson.annotation.JsonCreator"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.data.domain.Pageable"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.data.domain.PageRequest"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.data.domain.PageImpl"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.List"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.ArrayList"));

    topLevelClass.addTypeParameter(new TypeParameter("T"));

    topLevelClass.addAnnotation("@JsonIgnoreProperties(ignoreUnknown = true)");

    FullyQualifiedJavaType superClass = new FullyQualifiedJavaType("org.springframework.data.domain.PageImpl");
    superClass.addTypeArgument(new FullyQualifiedJavaType("T"));
    topLevelClass.setSuperClass(superClass);


    Method _defaultconstructor = new Method("CustomPageImpl");
    _defaultconstructor.setConstructor(true);
    _defaultconstructor.setVisibility(JavaVisibility.PUBLIC);
    _defaultconstructor.addBodyLine("super(new ArrayList<>());");
    topLevelClass.addMethod(_defaultconstructor);

    Method _constructor = new Method("CustomPageImpl");
    _constructor.setConstructor(true);
    _constructor.setVisibility(JavaVisibility.PUBLIC);
    FullyQualifiedJavaType contentType = new FullyQualifiedJavaType("java.util.List");
    contentType.addTypeArgument(new FullyQualifiedJavaType("T"));
    Parameter content = new Parameter(contentType, "content");
    content.addAnnotation("@JsonProperty(\"content\")");
    Parameter number = new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "number");
    number.addAnnotation("@JsonProperty(\"number\")");
    Parameter size = new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "size");
    size.addAnnotation("@JsonProperty(\"size\")");
    Parameter totalElements = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "totalElements");
    totalElements.addAnnotation("@JsonProperty(\"totalElements\")");
    Parameter pageable = new Parameter(new FullyQualifiedJavaType("com.fasterxml.jackson.databind.JsonNode"), "pageable");
    pageable.addAnnotation("@JsonProperty(\"pageable\")");
    Parameter last = new Parameter(new FullyQualifiedJavaType("java.lang.Boolean"), "last");
    last.addAnnotation("@JsonProperty(\"last\")");
    Parameter totalPages = new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "totalPages");
    totalPages.addAnnotation("@JsonProperty(\"totalPages\")");
    Parameter sort = new Parameter(new FullyQualifiedJavaType("com.fasterxml.jackson.databind.JsonNode"), "sort");
    sort.addAnnotation("@JsonProperty(\"sort\")");
    Parameter numberOfElements = new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "numberOfElements");
    numberOfElements.addAnnotation("@JsonProperty(\"numberOfElements\")");
    _constructor.addAnnotation("@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)");
    _constructor.addParameter(content);
    _constructor.addParameter(number);
    _constructor.addParameter(size);
    _constructor.addParameter(totalElements);
    _constructor.addParameter(pageable);
    _constructor.addParameter(last);
    _constructor.addParameter(totalPages);
    _constructor.addParameter(sort);
    _constructor.addParameter(numberOfElements);
    _constructor.addBodyLine("super(content, PageRequest.of(number, 1), 10);");
    topLevelClass.addMethod(_constructor);


    _constructor = new Method("CustomPageImpl");
    _constructor.setConstructor(true);
    _constructor.setVisibility(JavaVisibility.PUBLIC);
    content = new Parameter(contentType, "content");
    _constructor.addParameter(content);
    _constructor.addBodyLine("super(content);");
    topLevelClass.addMethod(_constructor);

    _constructor = new Method("CustomPageImpl");
    _constructor.setConstructor(true);
    _constructor.setVisibility(JavaVisibility.PUBLIC);
    content = new Parameter(contentType, "content");
    pageable = new Parameter(new FullyQualifiedJavaType("org.springframework.data.domain.Pageable"), "pageable");
    totalElements = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "totalElements");
    _constructor.addParameter(content);
    _constructor.addParameter(pageable);
    _constructor.addParameter(totalElements);
    _constructor.addBodyLine("super(content, pageable, totalElements);");
    topLevelClass.addMethod(_constructor);

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
