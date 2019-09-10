package com.pyg.manager.service;
import com.pyg.pojo.TbBrand;
import com.pyg.pojo.TbBrand;

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
}
