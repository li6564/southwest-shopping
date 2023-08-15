package cn.southwest.shop.service.impl;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.constant.SystemConstants;
import cn.southwest.shop.mapper.BigTypeMapper;
import cn.southwest.shop.pojo.BigType;
import cn.southwest.shop.pojo.SmallType;
import cn.southwest.shop.service.IBigTypeService;
import cn.southwest.shop.service.ISmallTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/11 19:50
 */
@Service
public class BigTypeServiceImpl extends ServiceImpl<BigTypeMapper, BigType> implements IBigTypeService {

    @Reference
    private ISmallTypeService smallTypeService;

    @Override
    public ResponseResult findAll() {
        LambdaQueryWrapper<BigType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BigType::getStatus, SystemConstants.BIG_TYPE_STATUS_NORMAL);
        List<BigType> bigTypeList = list(queryWrapper);
        return new ResponseResult(bigTypeList);
    }

    @Override
    public ResponseResult findCategories() {
        //获取BigType列表
        LambdaQueryWrapper<BigType> queryWrapper = new LambdaQueryWrapper<>();
        //判断BigType是否正常
        queryWrapper.eq(BigType::getStatus,SystemConstants.BIG_TYPE_STATUS_NORMAL);
        List<BigType> bigTypeList = list(queryWrapper);
        //遍历商品大类并且填充商品小类信息
        bigTypeList.forEach(bigType -> {
            List<SmallType> smallTypeList = smallTypeService.findSmallTypeByBigTypeId(bigType.getId());
            bigType.setSmallTypeList(smallTypeList);
        });
        return new ResponseResult(bigTypeList);
    }
}
