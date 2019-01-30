package com.kxl.handler;

import com.alibaba.fastjson.JSON;
import com.kxl.util.AjaxResponseBody;
import com.kxl.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录成功时返回给前端的数据
 * Created by Administrator on 2019/1/29.
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger log = LoggerFactory.getLogger(AjaxAuthenticationSuccessHandler.class);

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("200");
        responseBody.setMsg("Login Success!");
        log.info("TOKEN=" + jwtTokenUtil.generateToken(authentication.getName()));
        responseBody.setJwtToken(jwtTokenUtil.generateToken(authentication.getName()));

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
