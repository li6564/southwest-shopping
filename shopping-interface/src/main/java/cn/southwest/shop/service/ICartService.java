package cn.southwest.shop.service;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.pojo.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author：linan
 * @Date：2023/8/13 14:41
 */
public interface ICartService extends IService<Cart> {
    /**
     * 新增商品到购物车
     * @param productId
     * @return
     */
    public ResponseResult addCartProduct(Integer productId,String openId);

    /**
     * 删除购物车中商品
     * @param productId
     * @param openId
     * @return
     */
    public ResponseResult deleteCartProduct(Integer productId,String openId);

    /**
     * 根据用户openId获取购物车中的商品
     * @param openId
     * @return
     */
    public ResponseResult findCartProductByOpenId(String openId);
}
