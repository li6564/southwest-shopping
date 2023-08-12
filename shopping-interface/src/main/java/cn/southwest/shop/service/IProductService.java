package cn.southwest.shop.service;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/11 16:05
 */
public interface IProductService extends IService<Product> {
    /**
     * 查询商品轮播图
     * @return
     */
    public ResponseResult findSwiper();

    /**
     * 查询热卖商品推荐
     * @return
     */
    public ResponseResult findHot();

    /**
     * 根据商品小类id查询商品信息
     * @param smallTypeId
     * @return
     */
    public List<Product> findProductBySmallTypeId(Integer smallTypeId);

    /**
     * 根据商品Id获取商品详情
     * @param productId
     * @return
     */
    public ResponseResult findProductById(Integer productId);
}
