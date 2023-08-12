package cn.southwest.shop.service.impl;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.mapper.BigTypeMapper;
import cn.southwest.shop.pojo.BigType;
import cn.southwest.shop.service.IBigTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/11 19:50
 */
@Service
public class IBigTypeServiceImpl extends ServiceImpl<BigTypeMapper, BigType> implements IBigTypeService {
    @Override
    public ResponseResult findAll() {
        LambdaQueryWrapper<BigType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BigType::getStatus,1);
        List<BigType> bigTypeList = list(queryWrapper);
        return new ResponseResult(bigTypeList);
    }
}
