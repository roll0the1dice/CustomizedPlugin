package com.example.custom_plugin;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import com.example.custom_plugin.plugin.CustomizedPlugin;

import lombok.Data;


@AutoConfiguration
@ConfigurationProperties(prefix = "exmpale.custom-plugin")
@EnableConfigurationProperties(MyCustomizedPluginConfig.class)
@Data
@ComponentScan
public class MyCustomizedPluginConfig {
  @Bean
  public CustomizedPlugin customizedPlugin() {
    return new CustomizedPlugin();
  }
}
