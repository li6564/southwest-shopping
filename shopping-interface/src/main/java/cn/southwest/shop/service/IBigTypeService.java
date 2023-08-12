package cn.southwest.shop.service;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.pojo.BigType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author：linan
 * @Date：2023/8/11 19:49
 */
public interface IBigTypeService extends IService<BigType> {
    /**
     * 查询商品大类列表
     * @return
     */
    ResponseResult findAll();

    /**
     * 查询商品分类信息
     * @return
     */
    ResponseResult findCategories();
}
