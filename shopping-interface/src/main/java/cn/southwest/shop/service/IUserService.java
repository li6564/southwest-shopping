package cn.southwest.shop.service;

import cn.southwest.shop.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author：linan
 * @Date：2023/8/11 10:41
 */
public interface IUserService extends IService<Admin> {
    public Admin findById(Integer userId);
}
