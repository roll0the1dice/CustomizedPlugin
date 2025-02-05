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
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class AuthInterceptorPlugin extends PluginAdapter {
  private String targetPackage;

  private Properties properties;

  public AuthInterceptorPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public AuthInterceptorPlugin() {
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
  public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
    // List to store additional generated files
    List<GeneratedJavaFile> additionalFiles = new ArrayList<>();

    // 设置接口类的包名
    String packageName = this.targetPackage;

    // 创建接口类（例如 UserMapper）
    // 创建接口类（例如 UserMapper）
    String modelClassName = introspectedTable.getBaseRecordType();

    String[] strList = modelClassName.split("\\.");
    String _modelName = Arrays.asList(strList).get(strList.length - 1);
    // String interfaceName =
    // introspectedTable.getFullyQualifiedTable().getDomainObjectName() +
    // modelClassName;

    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "AuthInterceptor");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);

    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.aspectj.lang.ProceedingJoinPoint"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.aspectj.lang.annotation.Around"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.aspectj.lang.annotation.Aspect"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Component"));
    topLevelClass
        .addImportedType(new FullyQualifiedJavaType("org.springframework.web.context.request.RequestAttributes"));
    topLevelClass
        .addImportedType(new FullyQualifiedJavaType("org.springframework.web.context.request.RequestContextHolder"));
    topLevelClass.addImportedType(
        new FullyQualifiedJavaType("org.springframework.web.context.request.ServletRequestAttributes"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType(packageName + "." + "AuthCheckAnotation"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType(packageName + "." + "UserRoleEnum"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType(packageName + "." + "User"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("jakarta.servlet.http.HttpServletRequest"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType(modelClassName));

    // Add class documentation
    topLevelClass.addJavaDocLine("/**");
    topLevelClass.addJavaDocLine(" * This is a generated NotFoundException for demonstration purposes.");
    topLevelClass.addJavaDocLine(" */");

    topLevelClass.addAnnotation("@Aspect");
    topLevelClass.addAnnotation("@Component");

    var field = new Field("userService", new FullyQualifiedJavaType(_modelName + "ServiceImpl"));
    field.setVisibility(JavaVisibility.PRIVATE);
    field.addJavaDocLine("/** This is an example service. */");
    field.addAnnotation("@Autowired");
    topLevelClass.addField(field);

    Method _doInterceptor = new Method("doInterceptor");
    _doInterceptor.setVisibility(JavaVisibility.PUBLIC);
    _doInterceptor.addJavaDocLine("/**");
    _doInterceptor.addJavaDocLine("* UseCase: ");
    _doInterceptor.addJavaDocLine("* @AuthCheck(mustRole = UserRoleEnum.USER) ");
    _doInterceptor.addJavaDocLine("* public String getMethodName(@RequestParam String param) {");
    _doInterceptor.addJavaDocLine("*        return new String();");
    _doInterceptor.addJavaDocLine("* }");
    _doInterceptor.addJavaDocLine("*/");
    _doInterceptor.addAnnotation("@Around(\"@annotation(authCheck)\")");
    Parameter page = new Parameter(new FullyQualifiedJavaType("org.aspectj.lang.ProceedingJoinPoint"), "joinPoint");
    Parameter size = new Parameter(new FullyQualifiedJavaType(packageName + "." + "AuthCheck"), "authCheck");
    _doInterceptor.addParameter(page);
    _doInterceptor.addParameter(size);
    _doInterceptor.setReturnType(new FullyQualifiedJavaType("java.lang.Object"));
    String[] _tmpParameters = {
        "RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();",
        "HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();",
        "//if (userService.getCurrent(request) == null)",
        "//throw new BadRequestException(\"User session not found\");",
        "UserRoleEnum mustRoleEnum = authCheck.mustRole();",
        "if (mustRoleEnum == null) {",
        "return joinPoint.proceed();",
        "}",
        "UserRoleEnum userRoleEnum = UserRoleEnum.USER;",
        "//userRoleEnum = users.getUserRole();",
        "if (userRoleEnum == null) {",
        "throw new BadRequestException(\"Permission denied\");",
        "}",
        "if (UserRoleEnum.BAN.equals(userRoleEnum)) {",
        "throw new BadRequestException(\"Permission denied\");",
        "}",
        "if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {",
        "if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {",
        "throw new BadRequestException(\"Permission denied\");",
        "}",
        "}",
        "return joinPoint.proceed();" };
    List<String> _tmpstringList = Arrays.asList(_tmpParameters);
    Collection<String> _tmpstringCollection = _tmpstringList;
    _doInterceptor.addBodyLines(_tmpstringCollection);
    topLevelClass.addMethod(_doInterceptor);

    // Use DefaultJavaFormatter to format the generated Java file
    DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
    GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

    // Add the generated file to the list
    additionalFiles.add(generatedJavaFile);

    return additionalFiles;
  }
}
