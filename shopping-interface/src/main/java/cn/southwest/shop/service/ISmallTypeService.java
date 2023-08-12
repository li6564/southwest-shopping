package cn.southwest.shop.service;

import cn.southwest.shop.pojo.SmallType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * @Author：linan
 * @Date：2023/8/12 9:18
 */
public interface ISmallTypeService extends IService<SmallType> {

    /**
     * 根据商品大类id查询商品小类
     * @param bigTypeId
     * @return
     */
    public List<SmallType> findSmallTypeByBigTypeId(Integer bigTypeId);
}
