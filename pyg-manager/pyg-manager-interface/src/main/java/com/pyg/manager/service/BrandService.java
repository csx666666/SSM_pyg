package com.pyg.manager.service;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrand;
import com.pyg.utils.PageResult;

import java.util.List;

/**
 * @author DingZhenYun
 * @create 2019-09-10 下午 14:02
 */

public interface BrandService {

    /**
     * 需求:查询所有品牌数据
     */
    public List<TbBrand> findAll();
    /**
     * 需求:分页展示品牌列表
     * 参数:Integer page,Integer rows
     * 返回值:分页包装类对象PageResult
     */
    public PageResult findPage(Integer page,Integer rows);
}
