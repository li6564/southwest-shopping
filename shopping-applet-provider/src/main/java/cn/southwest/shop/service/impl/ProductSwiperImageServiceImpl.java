package cn.southwest.shop.service.impl;

import cn.southwest.shop.constant.SystemConstants;
import cn.southwest.shop.mapper.ProductSwiperImageMapper;
import cn.southwest.shop.pojo.ProductSwiperImage;
import cn.southwest.shop.service.IProductSwiperImageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/12 11:18
 */
@Service
public class ProductSwiperImageServiceImpl extends ServiceImpl<ProductSwiperImageMapper, ProductSwiperImage> implements IProductSwiperImageService {
    @Override
    public List<ProductSwiperImage> findProductSwiperImageByProductId(Integer productId) {
        LambdaQueryWrapper<ProductSwiperImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductSwiperImage::getStatus, SystemConstants.PRODUCT_SWIPER_IMAGE_STATUS_NORMAL)
                .eq(ProductSwiperImage::getProductid,productId)
                .orderByAsc(ProductSwiperImage::getSort);
        List<ProductSwiperImage> productSwiperImageList = list(queryWrapper);
        return productSwiperImageList;
    }
}
