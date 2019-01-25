package com.kxl.service;

import com.kxl.Dto.LoginDto;
import com.kxl.bo.UserBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */
public interface UserService {

    List<UserBo> selectByUname(LoginDto loginDto);

    /**
     * 登录信息写入cooke中
     * @param userBo
     * @param request
     * @param response
     */
    public void login(UserBo userBo, HttpServletRequest request, HttpServletResponse response);
}
