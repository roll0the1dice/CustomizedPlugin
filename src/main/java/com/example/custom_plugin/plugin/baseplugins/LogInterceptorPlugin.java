package com.example.custom_plugin.plugin.baseplugins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

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
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class LogInterceptorPlugin extends PluginAdapter {
  private String targetPackage;

  private Properties properties;

  public LogInterceptorPlugin(Properties properties) {
    super();
    this.properties = properties;
  }

  public LogInterceptorPlugin() {
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

    TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "LogInterceptor");
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);

    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.http.ResponseEntity"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.util.StopWatch"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.commons.lang3.StringUtils"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("cn.dev33.satoken.stp.StpUtil"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.extern.slf4j.Slf4j"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.UUID"));
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
    topLevelClass.addAnnotation("@Slf4j");

    var field = new Field("userService", new FullyQualifiedJavaType(_modelName + "ServiceImpl"));
    field.setVisibility(JavaVisibility.PRIVATE);
    field.addJavaDocLine("/** This is an example service. */");
    field.addAnnotation("@Autowired");
    topLevelClass.addField(field);

    Method _doInterceptor = new Method("doInterceptor");
    _doInterceptor.setVisibility(JavaVisibility.PUBLIC);
    _doInterceptor.addAnnotation("@Around(\"execution(* com.example.testplugin.generatedJavaFiles.*.*(..))\")");
    Parameter page = new Parameter(new FullyQualifiedJavaType("org.aspectj.lang.ProceedingJoinPoint"), "joinPoint");
    _doInterceptor.addParameter(page);
    _doInterceptor.setReturnType(new FullyQualifiedJavaType("java.lang.Object"));
    String[] _tmpParameters = {
        "StopWatch stopWatch = new StopWatch();",
        "stopWatch.start();",
        "RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();",
        "HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();",
        "String requestId = UUID.randomUUID().toString();",
        "String url = httpServletRequest.getRequestURI();",
        "Object[] args = joinPoint.getArgs();",
        "String reqParam = \"[\" + StringUtils.join(args, \", \") + \"]\";",
        "log.info(\"request start，id: {}, path: {}, ip: {}, params: {}\", requestId, url,",
        "httpServletRequest.getRemoteHost(), reqParam);",
        "if (!\"/users/doLogin\".equals(url) && !StpUtil.isLogin()) {",
        "return ResponseEntity.badRequest().body(\"Bad Request Error: \" + ErrorCode.NOT_LOGIN_ERROR.toString());",
        "}",
        "try {",
        "Object result = joinPoint.proceed();",
        "stopWatch.stop();",
        "long totalTimeMillis = stopWatch.getTotalTimeMillis();",
        "log.info(\"request end, id: {}, cost: {}ms\", requestId, totalTimeMillis);",
        "return result;",
        "}catch (Throwable e) {",
        "throw new RuntimeException(\"Internal Server Error\");",
        "}"
    };
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
