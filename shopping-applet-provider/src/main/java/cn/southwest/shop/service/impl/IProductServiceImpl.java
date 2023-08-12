package cn.southwest.shop.service.impl;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.common.dto.PageDTO;
import cn.southwest.shop.mapper.ProductMapper;
import cn.southwest.shop.pojo.Product;
import cn.southwest.shop.service.IProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/11 16:06
 */
@Service
public class IProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements IProductService {
    @Override
    public ResponseResult findSwiper() {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getIsSwiper,true)
                .orderByAsc(Product::getSwiperSort);
        List<Product> swiperProductList = list(queryWrapper);
        System.out.println(swiperProductList);
        return new ResponseResult(swiperProductList);
    }

    @Override
    public ResponseResult findHot() {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getIsHot,true)
                .eq(Product::getStatus,1)
                .orderByDesc(Product::getHotDatetime);
        //分页查询
        Page<Product> page = new Page<>(0,8);
        page(page,queryWrapper);
        //分页封装
        PageDTO<Product> pageDTO = new PageDTO<>();
        pageDTO.setRecords(page.getRecords());

        return new ResponseResult(pageDTO);
    }
}
