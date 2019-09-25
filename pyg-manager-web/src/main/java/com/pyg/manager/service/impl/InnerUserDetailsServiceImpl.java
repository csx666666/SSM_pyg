package com.pyg.manager.service.impl;/**
 * @author DingZhenYun
 * @create 2019-09-18 下午 16:47
 */


import com.pyg.manager.service.UserService;

import com.pyg.pojo.TbUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SSM_pyg->InnerUserDetailsServiceImpl
 * @description:
 **/
public class InnerUserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //定义角色封装集合
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //调用商家服务对象,查询商家密码
        TbUser user = userService.selectUserByUserName(s);
        return new User(s,user.getPassword(),authorities);
    }
}
