package cn.southwest.shop.controller;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.service.IProductService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：linan
 * @Date：2023/8/11 16:11
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Reference(parameters = {"unicast","false"})
    private IProductService productService;

    /**
     * 查询轮播商品
     * @return
     */
    @GetMapping("/findSwiper")
    public ResponseResult findSwiper(){
        ResponseResult swiper = productService.findSwiper();
        return swiper;
    }

}
