package cn.southwest.shop.controller;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.service.IBigTypeService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ServletResponseMethodArgumentResolver;

/**
 * @Author：linan
 * @Date：2023/8/11 20:04
 */
@RestController
@RequestMapping("/bigType")
public class BigTypeController {
    @Reference
    private IBigTypeService bigTypeService;

    /**
     * 查询所有商品大类
     * @return
     */
    @GetMapping("/findAll")
    public ResponseResult findAll(){
        ResponseResult bigTypeList = bigTypeService.findAll();
        return bigTypeList;
    }
}
