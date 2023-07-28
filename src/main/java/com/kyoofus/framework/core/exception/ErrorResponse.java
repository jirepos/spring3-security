package com.kyoofus.framework.core.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;

/** 에러가 발생하는 경우 클라이언트에 전달할 에러 메시지 객체 */
@Getter
@Builder
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    /**  400. HttpStatus의 코드값 */
    private final int status;
    /** HttpStatus의 이름. BAD_REQUEST **/
    private final String name;
    /** 에러 코드. {@link ErrorCode} enum 의 이름 */
    private final String code;
    /** 사용자에게 보여질 메시지 */
    private final String message;

    /**
     * 에러코드는 ResponseEntity에 담아 전달한다. 클라이언트에는 JSONㅇ로 변환하여 전달한다.
     * @param errorCode 에러코드
     * @return  ResponseEntity 객체
     */
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value()) // 400. HttpStatus의 코드값.
                        .name(errorCode.getHttpStatus().name()) // HttpStatus의 이름. BAD_REQUEST
                        .code(errorCode.name())    // enum 의 이름.  OTHER_ERROR
                        .message(errorCode.getDetail())  // enum의 detail. 정의되지 않는 유형의 오류입니다.
                        .build()
                );
    }
}///~