package com.pyg.manager.service.impl;/**
 * @author DingZhenYun
 * @create 2019-09-10 下午 16:08
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pyg.manager.service.BrandService;
import com.pyg.mapper.TbBrandMapper;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrandExample;
import com.pyg.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: SSM_pyg->BrandServiceImpl
 * @description:
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Override
    public List<TbBrand> findAll() {
        TbBrandExample brandExample=new TbBrandExample();
        return tbBrandMapper.selectByExample(brandExample);
    }
    /**
     * 需求:分页展示品牌列表
     * 参数:Integer page,Integer rows
     * 返回值:分页包装类对象PageResult
     */
    @Override
    public PageResult findPage(Integer page, Integer rows) {
        //创建example对象
        TbBrandExample example=new TbBrandExample();
        // 使用插件设置分页
        PageHelper.startPage(page,rows);
        //查询品牌数据
        //List={page[total,pagesie],list}
        List<TbBrand> list = tbBrandMapper.selectByExample(example);
        //创建pageINfo对象,获取查询分页数据
        PageInfo<TbBrand> pageInfo=new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(),list);



    }
}
