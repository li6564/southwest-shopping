package cn.southwest.shop.service;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.pojo.WxUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author：linan
 * @Date：2023/8/12 18:25
 */
public interface IWxUserService extends IService<WxUser> {
    /**
     * 根据openId查询微信用户
     * @param openId
     * @return
     */
    public WxUser findWxUserByOpenId(String openId);

    /**
     * 注册微信用户
     * @param wxUser
     * @return
     */
    public ResponseResult addWxUser(WxUser wxUser);

    /**
     * 跟新微信用户信息
     * @param wxUser
     * @return
     */
    public ResponseResult UpdataWxUser(WxUser wxUser);
}
