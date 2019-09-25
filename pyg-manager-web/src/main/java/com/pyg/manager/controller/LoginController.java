package com.pyg.manager.controller;/**
 * @author DingZhenYun
 * @create 2019-09-17 下午 17:08
 */

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SSM_pyg->LoginController
 * @description:
 **/
@RestController
@RequestMapping("/login")
public class LoginController {
    /**
     * 需求:获取当前用户的登录信息
     */
    @RequestMapping("/loadLoginName")
    public Map loadLoginName(){

        //获取用户名
        //使用安全框架获取用户登录名
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Map map=new HashMap();
        map.put("loginName",username);
        return map;
    }
}
