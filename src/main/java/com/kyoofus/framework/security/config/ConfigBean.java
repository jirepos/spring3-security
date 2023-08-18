package com.kyoofus.framework.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

// @Getter
// @Setter
// @Component
public class ConfigBean {
  // @Value("${server.servlet.context-path}")
  private String context; 
}
