package cn.southwest.shop.service.impl;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.constant.SystemConstants;
import cn.southwest.shop.mapper.CartMapper;
import cn.southwest.shop.pojo.Cart;
import cn.southwest.shop.pojo.Product;
import cn.southwest.shop.pojo.ProductCart;
import cn.southwest.shop.pojo.WxUser;
import cn.southwest.shop.service.ICartService;
import cn.southwest.shop.service.IProductCartService;
import cn.southwest.shop.service.IProductService;
import cn.southwest.shop.service.IWxUserService;
import cn.southwest.shop.utils.BeanCopyUtils;
import cn.southwest.shop.vo.CartProductVo;
import cn.southwest.shop.vo.CartVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Author：linan
 * @Date：2023/8/13 14:43
 */
@Service
public class ICartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Reference
    private IProductService productService;

    @Reference
    private IProductCartService productCartService;

    @Reference
    private IWxUserService userService;

    @Override
    public ResponseResult addCartProduct(Integer productId,String userId) {
        //根据商品id获取商品信息
        Product product = productService.getById(productId);
        //如果商品不存在返回错误信息
        if (Objects.isNull(product)){
            return new ResponseResult(SystemConstants.OPERATION_FAILURE_CODE);
        }

        //如果商品库存不足或状态异常返回库存不足信息
        if (product.getStatus() == 0 || product.getStock() == 0){
            return new ResponseResult(SystemConstants.PRODUCT_STOCK_LESS);
        }
        //获取当前登录用户信息
        LambdaQueryWrapper<WxUser> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(WxUser::getOpenId,userId)
                .eq(WxUser::getStatus,SystemConstants.USER_STATUS_NORMAL);
        WxUser user = userService.getOne(userQueryWrapper);
        //返回用户异常信息
        if (Objects.isNull(user)){
            return new ResponseResult(SystemConstants.USER_STATUS_EXCEPTION);
        }
        //获取用户购物车
        LambdaQueryWrapper<Cart> cartQueryWrapper = new LambdaQueryWrapper<>();
        cartQueryWrapper.eq(Cart::getUserId,userId);
        Cart cart = getOne(cartQueryWrapper);
        //如果用户还未创建购物车则创建购物车否则判断购物车状态
        if (Objects.isNull(cart)){
            Cart cart1 = new Cart();
            cart1.setUserId(userId);
            save(cart1);
            cart = getOne(cartQueryWrapper);
        }
        //如果用户购物车状态为异常则返回购物车异常信息
        if (cart.getStatus() == SystemConstants.CART_STATUS_ABNORMAL){
            return new ResponseResult(SystemConstants.CART_STATUS_EXCEPTION);
        }
        //判断购物车数量是否超出商品库存
        LambdaQueryWrapper<ProductCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ProductCart::getProductId,productId)
                .eq(ProductCart::getCartId,cart.getCartId())
                .eq(ProductCart::getStatus,SystemConstants.PRODUCT_CART_NORMAL);
        int num = productCartService.count(lambdaQueryWrapper);
        if (num+1>product.getStock()){
            return new ResponseResult(SystemConstants.PRODUCT_STOCK_LESS);
        }

        //添加商品到购物车
        ProductCart productCart = new ProductCart();
        productCart.setCartId(cart.getCartId()).setProductId(product.getId());
        productCartService.save(productCart);
        //购物车商品数量+1
        cart.setNumber(cart.getNumber()+1);
        updateById(cart);

        return new ResponseResult("success");
    }

    @Override
    public ResponseResult findCartProductByOpenId(String openId) {
        //获取用户购物车
        LambdaQueryWrapper<Cart> cartQueryWrapper = new LambdaQueryWrapper<>();
        cartQueryWrapper.eq(Cart::getUserId,openId);
        Cart cart = getOne(cartQueryWrapper);
        if (Objects.isNull(cart) || cart.getNumber() == 0){
            return new ResponseResult(SystemConstants.CART_NULL);
        }
        if (cart.getStatus() == SystemConstants.CART_STATUS_ABNORMAL){
            return new ResponseResult(SystemConstants.CART_STATUS_EXCEPTION);
        }
        //获取购物车中商品列表
        LambdaQueryWrapper<ProductCart> productCartQueryWrapper = new LambdaQueryWrapper<>();
        productCartQueryWrapper.eq(ProductCart::getCartId,cart.getCartId())
                .eq(ProductCart::getStatus,SystemConstants.PRODUCT_CART_NORMAL)
                .orderByAsc(ProductCart::getCreateTime);
        List<ProductCart> productCartList = productCartService.list(productCartQueryWrapper);
        //获取具体商品
        Set<Integer> productIdSet = new HashSet<>();
        productCartList.forEach(productCart -> {
            productIdSet.add(productCart.getProductId());
        });
        //购物车Vo对象
        CartVo cartVo = new CartVo();
        List<Product> productList = productService.listByIds(productIdSet);
        List<CartProductVo> cartProductVos = BeanCopyUtils.copyBeanList(productList, CartProductVo.class);
        //填充某种商品在购物车中的数量
        cartProductVos.forEach(cartProductVo -> {
            Integer productVoId = cartProductVo.getId();
            LambdaQueryWrapper<ProductCart> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductCart::getProductId,productVoId)
                    .eq(ProductCart::getStatus,SystemConstants.PRODUCT_CART_NORMAL);
            int num = productCartService.count(queryWrapper);
            cartProductVo.setNum(num);
        });
        return new ResponseResult(cartProductVos);
    }
}
