# 使用 MyBatis Generator 正确配置 generatorConfig.xml

本文档将指导你如何正确配置 `generatorConfig.xml` 文件，以便使用 MyBatis Generator 生成代码，并集成自定义插件。

## 1. 依赖

确保你的项目中引入了 MyBatis Generator 的相关依赖。如果你使用 Maven，可以在 `pom.xml` 中添加以下依赖：

```xml
	<dependencies>
    <!-- ······ -->

    <dependency>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-core</artifactId>
        <version>1.4.2</version> 
    </dependency>

    <!-- ······· -->
	</dependencies>

	<build>
    <plugins>

       <!-- ······· -->

        <plugin>
          <groupId>org.mybatis.generator</groupId>
          <artifactId>mybatis-generator-maven-plugin</artifactId>
          <version>1.4.2</version>
					<dependencies>
							<dependency>
								<groupId>com.mysql</groupId>
								<artifactId>mysql-connector-j</artifactId>
								<version>8.0.33</version>
							</dependency>
					</dependencies>
        </plugin>

        <!--  ······· -->

      </plugins>
	</build>

```


## 2. 配置 generatorConfig.xml
`generatorConfig.xml`是 MyBatis Generator 的核心配置文件。以下是一个示例配置，并附有详细注释：
```xml
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <context id="MysqlContext" targetRuntime="MyBatis3">

        <plugin type="com.example.custom_plugin.plugin.CustomizedPlugin" >
          <property name="targetPackage" value="com.example.custom_plugin.service"/>
        </plugin>

        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/Test?useSSL=false&amp;serverTimezone=Asia/Shanghai"
                        userId="admin"
                        password="123456">
                        <!-- <property name="nullCatalogMeansCurrent" value="true" /> -->
        </jdbcConnection>

        <!-- Java Model -->
        <javaModelGenerator targetPackage="com.example.custom_plugin.model" targetProject="src/main/java">
          <property name="enableSubPackages" value="true" />
          <property name="trimStrings" value="true" />
        </javaModelGenerator>

        <!-- SQL Map -->
        <sqlMapGenerator targetPackage="com.example.custom_plugin.mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>

        <!-- Java Client (Mapper) -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.example.custom_plugin.mapper" targetProject="src/main/java">
          <property name="enableSubPackages" value="true" />
        </javaClientGenerator>

        <!-- Database Table -->
        <table schema="Test" tableName="test_user" domainObjectName="TestUser">
          <generatedKey column="id" sqlStatement="JDBC" identity="true"/>
        </table>

    </context>

</generatorConfiguration>

```

**关键配置说明:**
- `plugin`: **配置自定义插件。** **`type` 为插件类的完整路径。** `targetPackage` 为包名，`value` 值指定所生成的JAVA文件路径。
- `jdbcConnection`: 数据库连接信息。
- `javaModelGenerator`: `Model` 类生成路径。`targetPackage` 为包名，`targetProject` 为项目路径。
- `sqlMapGenerator`: Mapper XML 文件生成路径。
- `javaClientGenerator`: Mapper 接口生成路径。
- `table`: 配置要生成的表，`tableName` 为数据库表名，`domainObjectName` 为生成的 `Java` 类名。

## 运行
在SpringBootApplication类的目录下新键一个plugin/plugin.java文件。写入一下内容，然后执行就可以看到文件生成了。如果要修改生成的目录的位置，可以修改插件中的值`<property name="targetPackage" value="com.example.custom_plugin.service"/>`, 把`value="com.example.custom_pluginservice`改成你要的目录. 譬如,`value="com.example.yourproject`.

```java
package com.example.smartBI.plugin;

import com.example.custom_plugin.plugin.CustomizedPlugin;

public class Plugin {
  public static void main(String[] args) {
    new CustomizedPlugin().run();
  }
}
```


## 自定义插件 (CustomizedPlugin.java)
创建一个实现了 `org.mybatis.generator.api.PluginAdapter` 接口的自定义插件类：
```java
package com.example.custom_plugin.plugin;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.ResourceUtils;

import com.example.custom_plugin.plugin.baseplugins.BaseServicePlugin;
import com.example.custom_plugin.plugin.baseplugins.ControllerPlugin;
import com.example.custom_plugin.plugin.baseplugins.CustomSpecsPlugin;
import com.example.custom_plugin.plugin.baseplugins.ModelAssemblerPlugin;
import com.example.custom_plugin.plugin.baseplugins.NotFoundAdvicePlugin;
import com.example.custom_plugin.plugin.baseplugins.NotFoundExceptionPlugin;
import com.example.custom_plugin.plugin.baseplugins.RepositoryPlugin;
import com.example.custom_plugin.plugin.baseplugins.ServiceImplPlugin;
import com.example.custom_plugin.plugin.baseplugins.ServicePlugin;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomizedPlugin extends PluginAdapter {
  // ··············
  // ··············

    public CustomizedPlugin() {
        super();
        // ·················
    }

    public boolean validate(List<String> warnings) {
        // ·························
        return true;
    }

    public static void main(String[] args) {
        try {
            // 指定 generatorConfig.xml 配置文件的路径
            String classpath = ResourceUtils.getFile("classpath:").getAbsolutePath();
            String configFilePath = Paths.get(classpath, "generatorConfig.xml").toString();

            // 准备 MyBatis Generator 的配置
            List<String> warnings = new ArrayList<>();
            boolean overwrite = false;
            File configFile = new File(configFilePath);

            // System.out.println(configFilePath);

            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);

            // 执行 MyBatis Generator
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);

            // 输出警告信息
            for (String warning : warnings) {
                System.out.println(warning);
            }

            System.out.println("MyBatis Generator sucesses !");

            System.exit(0); // 正常退出

        } catch (Exception e) {
            System.out.println("MyBatis Generator fails !");
            e.printStackTrace();
        }

    }

}

```


## 运行 CustomizedPlugin
编译并运行`CustomizedPlugin.java`类。

或者，使用以下命令：
```bash
cd /home/linux/SpringProjects/custom_plugin 
java @/tmp/cp_9s0clmxkxqh6n0bpcpyy27dvz.argfile com.example.custom_plugin.plugin.CustomizedPlugin
```

**注意：**
在使用 `MyBatis Generator` 时，通常不会直接使用 `java -classpath target/classes com.example.custom_plugin.plugin.CustomizedPlugin` 命令。`MyBatis Generator` 会使用一个参数文件（例如 `@/tmp/cp_...argfile`）来传递类路径和其他参数。你需要检查这个参数文件的内容，确认类路径是否正确设置。如果在使用 `Maven` 插件，则需要检查 `Maven` 插件的配置，确保插件的依赖和类路径正确。

## 5. 解决 ClassNotFoundException

如果运行过程中出现 `Caused by: java.lang.ClassNotFoundException: com.example.buddy_match.plugin.CustomizedPlugin` 错误，这意味着 `MyBatis Generator` 找不到你的自定义插件类。请检查以下几点：

- 插件类路径是否正确: 确保 `generatorConfig.xml` 中 `<plugin type="com.example.buddy_match.plugin.CustomizedPlugin">` 的路径与你的插件类完全一致。
- 插件类是否已编译: 确保你的插件类已经成功编译，并且 `.class` 文件存在于 `classpath` 中。如果你使用 `Maven`，运行 `mvn compile` 命令。
- 依赖是否正确: 如果你的插件依赖其他库，确保这些库已经正确添加到项目中。

通过以上步骤，你应该能够正确配置 `MyBatis Generator` 并使用自定义插件生成代码。如有其他问题，请参考 `MyBatis Generator` 官方文档。