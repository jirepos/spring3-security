package com.kyoofus.tutorial.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CkEditorUploadDto {
  private boolean uploaded; 
  private String url;
}
