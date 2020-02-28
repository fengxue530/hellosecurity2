package com.fx.hellosecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

@Component
@Slf4j
public class RestAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        Object principalObject = authentication.getPrincipal();
        Map<String, Object> principal = (Map<String, Object>) principalObject;
        if (principal.containsKey("USER_NAME")) {
            String username = principal.get("USER_NAME").toString();
            String password = (String) authentication.getCredentials();
            return authenticateByUsernameAndPassword(authentication, principal, username, password);
        }
        return new UsernamePasswordAuthenticationToken(null, null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Authentication authenticateByUsernameAndPassword(Authentication authentication, Map userPrincipal, String username, String password) {
        //根据用户名从数据库查询用户
        //如果查不到此用户
        if (!"张三".equals(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        try {
            //用户登陆验证逻辑
            if ("张三".equals(username) && "123".equals(password)) {
                //如果登陆通过并且 authorities 也通过，返回 UsernamePasswordAuthenticationToken
                return new UsernamePasswordAuthenticationToken(userPrincipal, null, null);
            }else{
                throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
