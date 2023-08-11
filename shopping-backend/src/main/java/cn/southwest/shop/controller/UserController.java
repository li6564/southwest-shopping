package cn.southwest.shop.controller;

import cn.southwest.shop.pojo.Admin;
import cn.southwest.shop.service.IUserService;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：linan
 * @Date：2023/8/11 10:43
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(parameters = {"unicast","false"})
    private IUserService userService;


    @GetMapping("/findById/{id}")
    public Admin findById(@PathVariable("id") Integer id){
        Admin admin = userService.findById(id);
        return admin;
    }

}
