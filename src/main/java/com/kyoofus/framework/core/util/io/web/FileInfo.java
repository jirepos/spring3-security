package com.kyoofus.framework.core.util.io.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class FileInfo {
  private String absolutePath;
  private String name; 
}
