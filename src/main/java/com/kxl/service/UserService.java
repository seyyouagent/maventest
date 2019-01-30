package com.kxl.service;

import com.kxl.Dto.LoginDto;
import com.kxl.bo.RoleBo;
import com.kxl.bo.UserBo;
import com.kxl.mapper.RoleMapper;
import com.kxl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/25.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDto loginDto = new LoginDto();
        loginDto.setUname(username);
        List<UserBo> users = userMapper.selectByUname(loginDto);

        List<RoleBo> roles = roleMapper.selectByUser(users.get(0));
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (RoleBo role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        if(users.size() == 0)
            throw new UsernameNotFoundException("there's no user founded!");
        else
            return new User(users.get(0).getUserName(),users.get(0).getPassword(),authorities);

    }
}
