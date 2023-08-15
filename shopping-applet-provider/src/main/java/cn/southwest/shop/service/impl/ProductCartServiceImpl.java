package cn.southwest.shop.service.impl;

import cn.southwest.shop.mapper.ProductCartMapper;
import cn.southwest.shop.pojo.ProductCart;
import cn.southwest.shop.service.IProductCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author：linan
 * @Date：2023/8/13 14:44
 */
@Service
public class ProductCartServiceImpl extends ServiceImpl<ProductCartMapper, ProductCart> implements IProductCartService {
}
