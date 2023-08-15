package cn.southwest.shop.service.impl;

import cn.southwest.shop.mapper.AddressMapper;
import cn.southwest.shop.pojo.Address;
import cn.southwest.shop.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author：linan
 * @Date：2023/8/14 15:01
 */
@Service
public class IAddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
