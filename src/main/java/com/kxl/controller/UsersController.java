package com.kxl.controller;

import com.kxl.Dto.UserDto;
import com.kxl.bo.UserBo;
import com.kxl.service.UserService;
import com.kxl.util.AjaxResponseBody;
import com.kxl.util.JwtTokenUtil;
import com.kxl.util.MD5;
import com.kxl.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;



/**
 * Created by Small Rain on 2019/1/31.
 */
@RestController
@Slf4j
public class UsersController extends BaseController {

    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 登录接口
     * @param userDto
     * @return
     */
    @PostMapping(value = "/login",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "登录接口", notes = "登录接口")
    public AjaxResponseBody login(@RequestBody UserDto userDto){

        logger.info("login:{}",  userDto);
        UserBo user = userService.loadUserByUsername(userDto.getUname());

        if(StringUtils.isNotEmpty(user.getUserName())) {
            redisUtil.setExpire(user.getUserName(),user.getUserName(),expiration+100000);
        }

        if (StringUtils.isNotEmpty(userDto.getPassword())
                && MD5.encrytMD5(userDto.getPassword()).equals(user.getPassword())
                && (userDto.getUname().equals(user.getUserName()))) {

            String jwtToken = jwtTokenUtil.generateToken(user.getUserName());
            jwtTokenUtil.setExpire(jwtToken,user.getUserName(),expiration+100000);

            return new AjaxResponseBody(HttpStatus.SC_OK,"登录成功！",null,jwtToken);
        }
        return new AjaxResponseBody(HttpStatus.SC_BAD_REQUEST,"用户名或密码错误！",null,null);
    }

    @PostMapping(value = "/selectUserByToken",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "根据用户查询token", notes = "根据用户查询token")
    public AjaxResponseBody selectUserByToken(@RequestBody UserDto userDto){
        logger.info("selectUserByToken:{}",  userDto);
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

    @PostMapping(value = "/loginOut",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "用户退出", notes = "用户退出")
    public AjaxResponseBody loginOut(@RequestBody UserDto userDto){
        logger.info("loginOut:{}",  userDto);

        if(StringUtils.isNotEmpty(userDto.getToken())) {
            String username = jwtTokenUtil.getUsernameFromToken(userDto.getToken());
            redisUtil.del(username);
            jwtTokenUtil.del(userDto.getToken());
        }
        return new AjaxResponseBody(HttpStatus.SC_OK,"请求成功！",null,null);
    }

    @PostMapping(value = "/updateUser",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "更新用户", notes = "更新用户")
    public AjaxResponseBody updateUser(@RequestBody UserDto userDto){

        userService.updatePassword(userDto);

        return new AjaxResponseBody(HttpStatus.SC_OK,"请求成功",null,null);
    }
}
