package com.kyoofus.framework.core.exception;

import lombok.Getter;

/**
 * Front-end Framework를 사용하고 있으므로 모든 요청은 Ajax 요청이됩니다.
 * System Exception을 제외하고, 예외 발생 시 이 예외를 던집니다.
 */
@Getter
public class AjaxExceptoin extends RuntimeException {

  private final ErrorCode errorCode;

  public AjaxExceptoin() {
    super();
    this.errorCode = ErrorCode.OTHER_ERROR;
  }

  public AjaxExceptoin(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public AjaxExceptoin(String message, Throwable var2, ErrorCode erroCode) {
    super(message, var2);
    this.errorCode = erroCode;
  }

  public AjaxExceptoin(Throwable message, ErrorCode erroCode) {
    super(message);
    this.errorCode = erroCode;
  }

  protected AjaxExceptoin(String message, Throwable var2, boolean var3, boolean var4, ErrorCode erroCode) {
    super(message, var2, var3, var4);
    this.errorCode = erroCode;
  }

}/// ~
