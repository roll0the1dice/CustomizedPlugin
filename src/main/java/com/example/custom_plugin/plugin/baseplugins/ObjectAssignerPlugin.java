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
import org.mybatis.generator.api.dom.java.TypeParameter;

public class ObjectAssignerPlugin extends PluginAdapter {

  private String targetPackage;

  private Properties properties;

  public ObjectAssignerPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public ObjectAssignerPlugin() {
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
    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "ObjectAssigner");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);

    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.lang.reflect.Field"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.lang.reflect.Method"));

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated assignNonNullValues for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");

    Method _assignNonNullValues = new Method("assignNonNullValues");
    _assignNonNullValues.setVisibility(JavaVisibility.PUBLIC);
    _assignNonNullValues.setStatic(true);
    _assignNonNullValues.addTypeParameter(new TypeParameter("T"));
    _assignNonNullValues.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "source"));
    _assignNonNullValues.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "target"));
    String[] _tmpParameters = {
        "if (source == null || target == null) {",
        "return; ",
        "}",
        "",
        "Class<?> clazz = source.getClass();",
        "",
        "while (clazz != null) {",
        "Field[] fields = clazz.getDeclaredFields();",
        "",
        "for (Field field : fields) {",
        "String fieldName = field.getName();",
        "String getterName = \"get\" + capitalize(fieldName);",
        "String setterName = \"set\" + capitalize(fieldName);",
        "try {",
        "Method getter = clazz.getDeclaredMethod(getterName);",
        "getter.setAccessible(true);",
        "Method setter = clazz.getDeclaredMethod(setterName, field.getType());",
        "setter.setAccessible(true);",
        "Object value = getter.invoke(source);",
        "if (value != null) {",
        "setter.invoke(target, value);",
        "}",
        "} catch (NoSuchMethodException e) {",
        "",
        "} catch (Exception e) {",
        "e.printStackTrace();",
        "}",
        "}",
        "clazz = clazz.getSuperclass();",
        "}",
    };
    List<String> _tmpstringList = Arrays.asList(_tmpParameters);
    Collection<String> _tmpstringCollection = _tmpstringList;
    _assignNonNullValues.addBodyLines(_tmpstringCollection);
    topLevelClass.addMethod(_assignNonNullValues);

    Method _capitalize = new Method("capitalize");
    _capitalize.setVisibility(JavaVisibility.PUBLIC);
    _capitalize.setStatic(true);
    _capitalize.setReturnType(new FullyQualifiedJavaType("java.lang.String"));
    _capitalize.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), "str"));
    _capitalize.addBodyLine("if (str == null || str.isEmpty()) {");
    _capitalize.addBodyLine("return str;");
    _capitalize.addBodyLine("}");
    _capitalize.addBodyLine("return str.substring(0, 1).toUpperCase() + str.substring(1);");
    topLevelClass.addMethod(_capitalize);

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
