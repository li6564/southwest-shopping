package cn.southwest.shop.service;

import cn.southwest.shop.pojo.ProductSwiperImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/12 11:16
 */
public interface IProductSwiperImageService extends IService<ProductSwiperImage> {
    /**
     * 根据商品id查询商品详情轮播图
     * @param productId
     * @return
     */
    public List<ProductSwiperImage> findProductSwiperImageByProductId(Integer productId);
}
