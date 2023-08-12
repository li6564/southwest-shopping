package cn.southwest.shop.service.impl;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.enmus.AppHttpCodeEnum;
import cn.southwest.shop.mapper.WxUserMapper;
import cn.southwest.shop.pojo.WxUser;
import cn.southwest.shop.service.IWxUserService;
import cn.southwest.shop.utils.JwtUtil;
import cn.southwest.shop.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Jwt;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @Author：linan
 * @Date：2023/8/12 18:27
 */
@Service
public class IWxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser>  implements IWxUserService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public WxUser findWxUserByOpenId(String openId) {
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WxUser::getOpenId,openId);
        WxUser wxUser = getOne(queryWrapper);
        return wxUser;
    }

    @Override
    public ResponseResult addWxUser(WxUser wxUser) {
        //注册微信用户
        boolean flag = save(wxUser);
        //获取openid
        String openId = wxUser.getOpenId();
        //生成token返回给前端小程序
        String token = JwtUtil.createJWT(openId);
        redisCache.setCacheObject("login"+token,wxUser,2, TimeUnit.HOURS);
        return new ResponseResult<>().ok("success",token);
    }

    @Override
    public ResponseResult UpdataWxUser(WxUser user) {
        //跟新微信用户
        WxUser wxUser = new WxUser();
        wxUser.setNickName(user.getNickName()).setAvatarUrl(user.getAvatarUrl());
        updateById(wxUser);
        //获取openid
        String openId = user.getOpenId();
        //生成token返回给前端小程序
        String token = JwtUtil.createJWT(openId);
        redisCache.setCacheObject("login"+token,wxUser,2, TimeUnit.HOURS);
        return new ResponseResult<>().ok("success",token);
    }
}
