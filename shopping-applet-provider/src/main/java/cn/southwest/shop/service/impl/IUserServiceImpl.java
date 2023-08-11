package cn.southwest.shop.service.impl;

import cn.southwest.shop.mapper.AdminMapper;
import cn.southwest.shop.pojo.Admin;
import cn.southwest.shop.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author：linan
 * @Date：2023/8/11 10:42
 */
@Service
public class IUserServiceImpl extends ServiceImpl<AdminMapper,Admin> implements IUserService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findById(Integer userId) {
        System.out.println(userId);
        Admin admin = adminMapper.findById(userId);
        return admin;
    }
}
