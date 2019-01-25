package com.kxl.controller;

import com.alibaba.druid.util.StringUtils;
import com.kxl.Dto.LoginDto;
import com.kxl.bo.UserBo;
import com.kxl.exception.MyException;
import com.kxl.service.UserService;
import com.kxl.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginDto loginDto) throws MyException{

        if (StringUtils.isEmpty(loginDto.getVerificationCode())) {
            throw  new MyException("000010");
        }

        List<UserBo> userBoList = userService.selectByUname(loginDto);

        if (userBoList.size() == 1) {
            UserBo userDataBase = userBoList.get(0);
            System.out.println(MD5.encrytMD5(loginDto.getPassword()));
            if (!StringUtils.isEmpty(loginDto.getPassword())
                    && MD5.encrytMD5(loginDto.getPassword()).equals(userDataBase.getPassword())
                    && (loginDto.getUname().equals(userDataBase.getUserName()))) {
//                userService.login(userDataBase, request, response);

                return new ResponseEntity(userDataBase, HttpStatus.MOVED_PERMANENTLY);
            }
            throw  new MyException("000004");
        } else {

            throw  new MyException("000005");
        }
    }
}
