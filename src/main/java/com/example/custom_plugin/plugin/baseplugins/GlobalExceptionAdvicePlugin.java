package com.example.custom_plugin.plugin.baseplugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class GlobalExceptionAdvicePlugin extends PluginAdapter {
    private String targetPackage;

    private Properties properties;

    public GlobalExceptionAdvicePlugin(Properties properties) {
        super();
        this.properties = properties;
    }

    public GlobalExceptionAdvicePlugin() {
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
        // String modelClassName = introspectedTable.getBaseRecordType();

        // String[] strList = modelClassName.split("\\.");
        // String _modelName = Arrays.asList(strList).get(strList.length - 1);
        // String interfaceName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + modelClassName;
        // System.out.println(packageName);
        // System.out.println(modelClassName);
        TopLevelClass topLevelClass = new TopLevelClass(packageName + "."  + "GlobalExceptionAdvice");
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);

        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.lang.String"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.http.HttpStatus"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ExceptionHandler"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseStatus"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestControllerAdvice"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.http.ResponseEntity"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.http.converter.HttpMessageNotReadableException"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.MissingServletRequestParameterException"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.method.annotation.MethodArgumentTypeMismatchException"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.v3.oas.annotations.Hidden"));

        // Add class documentation
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * This is a generated NotFoundException for demonstration purposes.");
        topLevelClass.addJavaDocLine(" */");

        topLevelClass.addAnnotation("@RestControllerAdvice");
        topLevelClass.addAnnotation("@Hidden");

        Method _notFoundHandler = new Method("GlobalExceptionAdvice");
        _notFoundHandler.addAnnotation("@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)");
        _notFoundHandler.setVisibility(JavaVisibility.PUBLIC);
        Parameter parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.RuntimeException"), "ex");
        _notFoundHandler.addParameter(parameter);
        FullyQualifiedJavaType _retType = new FullyQualifiedJavaType("org.springframework.http.ResponseEntity");
        _retType.addTypeArgument(new FullyQualifiedJavaType("?"));
        _notFoundHandler.setReturnType(_retType);
        _notFoundHandler.addBodyLine("return ResponseEntity.status(500).body(\"Internal Server Error: \" + ex.getMessage());");
        topLevelClass.addMethod(_notFoundHandler);

        Method _badRequestExceptionHandler = new Method("GlobalExceptionAdvice");
        FullyQualifiedJavaType _badRequestExceptionType =  new FullyQualifiedJavaType("BusinessException");
        _badRequestExceptionHandler.addAnnotation(String.format("@ExceptionHandler(%s.class)", _badRequestExceptionType.getFullyQualifiedName()));
        _badRequestExceptionHandler.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(_badRequestExceptionType, "ex");
        _badRequestExceptionHandler.addParameter(parameter);
        _badRequestExceptionHandler.setReturnType(_retType);
        _badRequestExceptionHandler.addBodyLine("return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(\"Bad Request Error: \" + ex.getMessage());");
        topLevelClass.addMethod(_badRequestExceptionHandler);

        Method _invalidInputHandler = new Method("GlobalExceptionAdvice");
        FullyQualifiedJavaType _httpMessageNotReadableExceptionType =  new FullyQualifiedJavaType("org.springframework.http.converter.HttpMessageNotReadableException");
        _invalidInputHandler.addAnnotation(String.format("@ExceptionHandler(%s.class)", _httpMessageNotReadableExceptionType.getFullyQualifiedName()));
        _invalidInputHandler.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(_httpMessageNotReadableExceptionType, "ex");
        _invalidInputHandler.addParameter(parameter);
        _invalidInputHandler.setReturnType(_retType);
        _invalidInputHandler.addBodyLine("return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(\"Invalid Input Error: \" + ex.getMessage());");
        topLevelClass.addMethod(_invalidInputHandler);

        Method _methodArgumentTypeMismatchExceptionHandler = new Method("GlobalExceptionAdvice");
        FullyQualifiedJavaType _methodArgumentTypeMismatchExceptionType =  new FullyQualifiedJavaType("org.springframework.web.method.annotation.MethodArgumentTypeMismatchException");
        _methodArgumentTypeMismatchExceptionHandler.addAnnotation(String.format("@ExceptionHandler(%s.class)", _methodArgumentTypeMismatchExceptionType.getFullyQualifiedName()));
        _methodArgumentTypeMismatchExceptionHandler.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(_methodArgumentTypeMismatchExceptionType, "ex");
        _methodArgumentTypeMismatchExceptionHandler.addParameter(parameter);
        _methodArgumentTypeMismatchExceptionHandler.setReturnType(_retType);
        _methodArgumentTypeMismatchExceptionHandler.addBodyLine("return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(\"Invalid Input Error: \" + ex.getMessage());");
        topLevelClass.addMethod(_methodArgumentTypeMismatchExceptionHandler);

        Method _missingServletRequestParameterExceptionHandler = new Method("GlobalExceptionAdvice");
        FullyQualifiedJavaType _missingServletRequestParameterExceptionType =  new FullyQualifiedJavaType("org.springframework.web.bind.MissingServletRequestParameterException");
        _missingServletRequestParameterExceptionHandler.addAnnotation(String.format("@ExceptionHandler(%s.class)", _missingServletRequestParameterExceptionType.getFullyQualifiedName()));
        _missingServletRequestParameterExceptionHandler.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(_missingServletRequestParameterExceptionType, "ex");
        _missingServletRequestParameterExceptionHandler.addParameter(parameter);
        _missingServletRequestParameterExceptionHandler.setReturnType(_retType);
        _missingServletRequestParameterExceptionHandler.addBodyLine("return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(\"Invalid Input Error: \" + ex.getMessage());");
        topLevelClass.addMethod(_missingServletRequestParameterExceptionHandler);

        Method _noSuchElementExceptionHandler = new Method("GlobalExceptionAdvice");
        FullyQualifiedJavaType _noSuchElementExceptionHandlerType =  new FullyQualifiedJavaType("java.util.NoSuchElementException");
        _noSuchElementExceptionHandler.addAnnotation(String.format("@ExceptionHandler(%s.class)", _noSuchElementExceptionHandlerType.getFullyQualifiedName()));
        _noSuchElementExceptionHandler.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(_noSuchElementExceptionHandlerType, "ex");
        _noSuchElementExceptionHandler.addParameter(parameter);
        _noSuchElementExceptionHandler.setReturnType(_retType);
        _noSuchElementExceptionHandler.addBodyLine("return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(\"Invalid Input Error: \" + ex.getMessage());");
        topLevelClass.addMethod(_noSuchElementExceptionHandler);

        // Use DefaultJavaFormatter to format the generated Java file
        DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

        // Add the generated file to the list
        additionalFiles.add(generatedJavaFile);

        return additionalFiles;
    }
}
