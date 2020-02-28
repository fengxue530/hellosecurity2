package com.fx.hellosecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class RestAuthenticationDetailsSource implements
        AuthenticationDetailsSource<HttpServletRequest, RestAuthenticationDetails> {
    @Override
    public RestAuthenticationDetails buildDetails(HttpServletRequest httpServletRequest) {
        return new RestAuthenticationDetails(httpServletRequest);
    }
}
