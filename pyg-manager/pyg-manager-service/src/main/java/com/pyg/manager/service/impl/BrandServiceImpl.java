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
import com.pyg.utils.PygResult;
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
        PageInfo<TbBrand> pageInfo=new PageInfo<TbBrand>(list);
        return new PageResult(pageInfo.getTotal(),list);

    }
    /**
     * 需求:添加品牌数据
     * 参数:TbBrand brand
     * 返回值:PygResult
     *
     */
    @Override
    public PygResult add(TbBrand brand) {
        try {
            tbBrandMapper.insertSelective(brand);
            return new PygResult(true,"保存成功");
        }catch (Exception e){
            e.printStackTrace();
            return new PygResult(false,"保存失败");
        }
    }
    /**
     * 需求:根据id查询品牌数据
     * 参数:Long id
     * 返回值:TbBrand
     */
    @Override
    public TbBrand findOne(Long id) {
        TbBrand brand = tbBrandMapper.selectByPrimaryKey(id);
        return brand;
    }
    /**
     * 需求:更新品牌数据
     * 参数:TbBrand brand
     * 返回值:PygResult
     */
    @Override
    public PygResult update(TbBrand brand) {
        try{
            tbBrandMapper.updateByPrimaryKeySelective(brand);
            return new PygResult(true, "修改成功");
        }catch (Exception e){
            e.printStackTrace();
            //保存成功
            return new PygResult(false, "修改失败");
        }
    }
    /**
     * 需求:更新品牌数据
     * 参数:TbBrand brand
     * 返回值:PygResult
     */
    @Override
    public PageResult findBrandByPage(TbBrand brand, Integer page, Integer rows) {
        // 创建example对象
        TbBrandExample tbBrandExample=new TbBrandExample();
        //创建criteria对象
        TbBrandExample.Criteria criteria=tbBrandExample.createCriteria();
        //设置参数
        //判断参数是否存在
       if(brand.getName()!=null&&!"".equals(brand.getName())){
           criteria.andNameLike("%"+brand.getName()+"%");
       }
       if(brand.getFirstChar()!=null&&!"".equals(brand.getFirstChar())){
           criteria.andFirstCharEqualTo(brand.getFirstChar());
       }

        //查询之前,必须设置分页
        PageHelper.startPage(page,rows);

        //执行查询
        List<TbBrand> list = tbBrandMapper.selectByExample(tbBrandExample);
        //创建PageINfo,获取分页数据
        PageInfo<TbBrand> pageInfo=new PageInfo<>(list);
        //返回分页包装对象
        return new PageResult(pageInfo.getTotal(),list);
    }
    /**
     * 需求: 批量删除品牌数据
     * 参数: Long[] id
     * 返回值:PygResult
     */
    @Override
    public PygResult deleteIds(Long[] ids) {
        try{
            for(Long id:ids){
                tbBrandMapper.deleteByPrimaryKey(id);
            }
            return new PygResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new PygResult(false,"删除失败");
        }
    }
}
