package com.kxl.controller;

import com.jayway.restassured.internal.http.Status;
import com.kxl.Dto.UserDto;
import com.kxl.bo.UserBo;
import com.kxl.service.UserService;
import com.kxl.util.AjaxResponseBody;
import com.kxl.util.JwtTokenUtil;
import com.kxl.util.MD5;
import com.sun.tools.internal.ws.wsdl.document.http.HTTPConstants;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.http.HttpStatus;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by Administrator on 2019/1/31.
 */
@RestController
public class UsersController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Value("${jwt.expiration}")
    private Long expiration;

    @RequestMapping(value = "/login",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public AjaxResponseBody login(@RequestBody UserDto userDto){

        UserBo user = userService.loadUserByUsername(userDto.getUname());

        if (StringUtils.isNotEmpty(userDto.getPassword())
                && MD5.encrytMD5(userDto.getPassword()).equals(user.getPassword())
                && (userDto.getUname().equals(user.getUserName()))) {

            String jwtToken = jwtTokenUtil.generateToken(user.getUserName());
            jwtTokenUtil.setExpire(jwtToken,user.getUserName(),expiration+100000);

            return new AjaxResponseBody(HttpStatus.SC_OK,"登录成功！",null,jwtToken);
        }
        return new AjaxResponseBody(HttpStatus.SC_BAD_REQUEST,"用户名或密码错误！",null,null);
    }

    @RequestMapping(value = "/selectUserByToken",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public AjaxResponseBody selectUserByToken(@RequestBody UserDto userDto){

        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        if(userDto.getToken() != null && userDto.getToken() != "") {
            String username = jwtTokenUtil.getUsernameFromToken(userDto.getToken());
            if(username != null && username != "") {
                ajaxResponseBody.setMsg("请求成功");
                ajaxResponseBody.setResult(username);
                ajaxResponseBody.setStatus(HttpStatus.SC_OK);
            }
        }
        return ajaxResponseBody;
    }
}
