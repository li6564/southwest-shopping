package cn.southwest.shop.controller;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.service.ICartService;
import cn.southwest.shop.utils.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：linan
 * @Date：2023/8/13 16:23
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Reference
    private ICartService cartService;

    @GetMapping("/addCartProduct")
    public ResponseResult addCartProduct(@RequestParam("id") Integer id){
        String openId = SecurityUtils.getUserId();
        return cartService.addCartProduct(id,openId);
    }

    @GetMapping("/findAllCartProduct")
    public ResponseResult findAllCartProduct(){
        Boolean login = SecurityUtils.isLogin();
        if (!login){
            return new ResponseResult("请先登录！");
        }
        String openId = SecurityUtils.getUserId();
        ResponseResult result = cartService.findCartProductByOpenId(openId);
        return result;
    }
}
