package com.pyg.shop.service.impl;/**
 * @author DingZhenYun
 * @create 2019-09-18 下午 16:47
 */

import com.pyg.manager.service.SellerService;
import com.pyg.pojo.TbSeller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SSM_pyg->UserDetailsServiceImpl
 * @description:
 **/
public class UserDetailsServiceImpl implements UserDetailsService {
    private SellerService sellerService;

    public SellerService getSellerService() {
        return sellerService;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //定义角色封装集合
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //调用商家服务对象,查询商家密码
        TbSeller seller = sellerService.findOne(s);
        return new User(s,seller.getPassword(),authorities);
    }
}
