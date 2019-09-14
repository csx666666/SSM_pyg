package com.pyg.manager.controller;/**
 * @author DingZhenYun
 * @create 2019-09-10 下午 16:15
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.manager.service.BrandService;
import com.pyg.pojo.TbBrand;
import com.pyg.utils.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: SSM_pyg->BrandController
 * @description:
 **/
@RestController
@RequestMapping("/brand")
public class BrandController {
    //注入远程服务对象
    @Reference(timeout = 100000000)
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        List<TbBrand> list = brandService.findAll();
        return list;
    }
    /**
     * 需求:分页展示品牌列表
     * 参数:Integer page,Integer rows
     * 返回值:分页包装类对象PageResult
     */
    @RequestMapping("/findPage")
    public PageResult findPage(Integer page,Integer rows){
        PageResult result = brandService.findPage(page, rows);
        return result;
    }
}