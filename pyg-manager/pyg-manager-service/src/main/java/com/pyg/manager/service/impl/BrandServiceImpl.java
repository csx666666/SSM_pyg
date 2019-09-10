package com.pyg.manager.service.impl;/**
 * @author DingZhenYun
 * @create 2019-09-10 下午 16:08
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.pyg.manager.service.BrandService;
import com.pyg.mapper.TbBrandMapper;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrandExample;
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
}
