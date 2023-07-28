package com.kyoofus.framework.core.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 전역에서 사용할 에러 코드 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
  
    /** 
     * 정의되지 않은 유형의 오류. 
     * 정의되지 않은 오류는 모두 BAD_REQUEST로 처리합니다. 
     * 400 BAD REQUSET
     */
    OTHER_ERROR(HttpStatus.BAD_REQUEST, "정의되지 않는 유형의 오류입니다."),
    /** 
     * 로그인 시 존재하지 않는 사용자인 경우 이 오류코드를 사용한다. 
     * 그러나, 사용자에게 상세한 정보를 제공하지 않아야 하므로 {@link #UNAUTHORIZED_USER}를 사용한다. 
     */
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."),

    /** 
     * 로그인  하지 않은 사용자. 로그인이 필요한 서비스에 접근할 때, 권한이 필요한 서비스에 접근할 때 권한이 없으면 
     * 이 오류코드를 사용한다. 
     * 401 Unauthorized
     */
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "로그인 하지 않은 사용자입니다. 로그인을 먼저 해야 합니다."),
    
    /**
     * 사용자에게 메시지를 전달할 필요가 없는 시스템 오류는 이 오류코드를 사용한다. 
     * 500 Internal Server Error
     */
    INTERAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "시스템 오류입니다.");

    /**
     * HttpStatus에 대한 정보를 담고 있는 객체. 
     */
    private final HttpStatus httpStatus;
    /**
     * 상세한 정보를 여기에 추가적으로 설정한다.
     */
    private final String detail;
}///~
