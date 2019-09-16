package com.pyg.manager.controller;/**
 * @author DingZhenYun
 * @create 2019-09-10 下午 16:15
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.manager.service.BrandService;
import com.pyg.pojo.TbBrand;
import com.pyg.utils.PageResult;
import com.pyg.utils.PygResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 需求:添加品牌数据
     * 参数:TbBrand brand
     * 返回值:PygResult
     *
     */
    @RequestMapping("/add")
    public PygResult add(@RequestBody TbBrand brand){
        PygResult result = brandService.add(brand);
        return result;
    }
    /**
     * 需求:根据id查询品牌数据
     * 请求:../brand/findOne?id=?
     * 参数:Long id
     * 返回值:TbBrand
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        //调用远程服务
        TbBrand brand = brandService.findOne(id);
        return brand;
    }

    /**
     * 需求:更新品牌数据
     * 请求:/brand/update
     * 参数:TbBrand brand
     * 返回值:PygResult
     */
    @RequestMapping("/update")
    public PygResult update(@RequestBody TbBrand brand){
        //调用远程服务
        PygResult result = brandService.update(brand);
        return result;

    }
    /**
     * 需求:品牌条件查询
     * 参数:TbBrand brand
     * 返回值:PygResult
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand,
                             @RequestParam(defaultValue = "1")Integer page,
                             @RequestParam(defaultValue = "10") Integer rows){
        PageResult result = brandService.findBrandByPage(tbBrand, page, rows);
        return result;
    }

    /**
     * 需求: 批量删除品牌数据
     * 请求: dele?ids=??
     * 参数: Long[] id
     * 返回值:PygResult
     */
    @RequestMapping("/dele")
    public PygResult dele(Long[] ids){
        PygResult pygResult = brandService.deleteIds(ids);
        return pygResult;
    }
}
