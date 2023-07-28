package com.kyoofus.framework.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.kyoofus.framework.core.util.ServletUtils;

import static org.springframework.security.config.Customizer.withDefaults;

public class ConfigBasicAuthenticaiton {

    private static AuthenticationEntryPoint authenticationEntryPoint() {
        // 로그인페이지 이동, 401오류 코드 전달
        return (request, response, e) -> {
            ServletUtils.responseUnauthorized(request, response);
        };
    }

    public static void customize(HttpSecurity http) throws Exception {
        http.httpBasic(customizer -> customizer
                .realmName("my-basic-realm") // 기본값 Spring Security
                .authenticationEntryPoint(authenticationEntryPoint())); // 인증 실패시 401
    }
    public static void httpBasicDefautt(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());
    }
}