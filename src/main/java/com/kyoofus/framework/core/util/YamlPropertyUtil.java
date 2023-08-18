package com.kyoofus.framework.core.util;
  
import org.springframework.context.ApplicationContext;

public class YamlPropertyUtil {
  
  public static String getProperty(String propertyName) {
    return getProperty(propertyName, null);
  }
  public static String getProperty(String propertyName, String defaultValue) {
    ApplicationContext applicationContext = 
           ApplicationContextHolder.geApplicationContext();
    String value = applicationContext.getEnvironment().getProperty(propertyName);
    return (value == null)? value : defaultValue; 
  }
}