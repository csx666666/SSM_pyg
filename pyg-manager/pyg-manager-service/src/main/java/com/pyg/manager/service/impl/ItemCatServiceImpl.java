package com.pyg.manager.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pyg.mapper.TbItemCatMapper;
import com.pyg.pojo.TbItemCat;
import com.pyg.pojo.TbItemCatExample;
import com.pyg.pojo.TbItemCatExample.Criteria;
import com.pyg.manager.service.ItemCatService;

import com.pyg.utils.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbItemCat> findAll() {
		return itemCatMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbItemCat> page=   (Page<TbItemCat>) itemCatMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbItemCat itemCat) {
		itemCatMapper.insert(itemCat);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbItemCat itemCat){
		itemCatMapper.updateByPrimaryKey(itemCat);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbItemCat findOne(Long id){
		return itemCatMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			//查询所有父亲节点为的id的子节点--二
			List<TbItemCat> tbItemCatsTwo = findItemCatByParentId(id);
			if(tbItemCatsTwo!=null && tbItemCatsTwo.size()>0){
				for (TbItemCat t: tbItemCatsTwo) {
					List<TbItemCat> itemCatsThree = findItemCatByParentId(t.getId());
					if(itemCatsThree!=null&&itemCatsThree.size()>0){
						for (TbItemCat t2:itemCatsThree) {
							itemCatMapper.deleteByPrimaryKey(t2.getId());
						}
					}
					//只到第二层
					deleteItemCatByParentId(t.getId());

				}
			}
				//删除自己的id
				itemCatMapper.deleteByPrimaryKey(id);
        }
	}


		@Override
	public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		
		if(itemCat!=null){			
						if(itemCat.getName()!=null && itemCat.getName().length()>0){
				criteria.andNameLike("%"+itemCat.getName()+"%");
			}
	
		}
		
		Page<TbItemCat> page= (Page<TbItemCat>)itemCatMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	/**
	 * 需求:根据父id查询子节点
	 * 参数:Long parentId
	 * 返回值:List<tbItemCat>
	 */
	@Override
	public List<TbItemCat> findItemCatByParentId(Long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		// 设置查询参数 根据父id查询子节点
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}

	/**
	 * 需求:根据父id删除子节点
	 * 参数:Long parentId
	 * 返回值：boolear
	 */
	public boolean deleteItemCatByParentId(Long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		// 设置查询参数 根据父id查询子节点
		criteria.andParentIdEqualTo(parentId);
		int i = itemCatMapper.deleteByExample(example);
		return (i<=0)?false:true;
	}

}
