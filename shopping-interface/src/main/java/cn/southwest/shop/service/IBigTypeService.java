package cn.southwest.shop.service;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.pojo.BigType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author：linan
 * @Date：2023/8/11 19:49
 */
public interface IBigTypeService extends IService<BigType> {
    ResponseResult findAll();
}
