package com.kyoofus.tutorial.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenDto {
  private String accessToken; 
  private String userId; 
  private String userName; 
  private String userEmail; 
}
