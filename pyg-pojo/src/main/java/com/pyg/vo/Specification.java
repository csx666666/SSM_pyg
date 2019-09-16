package com.pyg.vo;/**
 * @author DingZhenYun
 * @create 2019-09-16 下午 15:52
 */

import com.pyg.pojo.TbSpecification;
import com.pyg.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @program: SSM_pyg->Specification
 * @description:
 **/
public class Specification implements Serializable {
    //包装规格对象
    private TbSpecification tbSpecification;

    //包装规格列表
    private List<TbSpecificationOption> specificationList;

    public TbSpecification getTbSpecification() {
        return tbSpecification;
    }

    public void setTbSpecification(TbSpecification tbSpecification) {
        this.tbSpecification = tbSpecification;
    }

    public List<TbSpecificationOption> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List<TbSpecificationOption> specificationList) {
        this.specificationList = specificationList;
    }
}
