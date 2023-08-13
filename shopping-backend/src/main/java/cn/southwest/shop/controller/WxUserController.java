package cn.southwest.shop.controller;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.pojo.WxUser;
import cn.southwest.shop.properties.WeixinProperties;
import cn.southwest.shop.service.IWxUserService;
import cn.southwest.shop.utils.HttpClientUtil;
import cn.southwest.shop.utils.JsonUtils;
import cn.southwest.shop.utils.JwtUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.experimental.Accessors;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author：linan
 * @Date：2023/8/12 18:30
 */
@RestController
@RequestMapping("/users")
public class WxUserController {

    @Reference
    private IWxUserService wxUserService;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @PostMapping("/wxlogin")
    public ResponseResult wxlogin(@RequestBody WxUser user){
        String code = user.getCode();
        //拼接请求链接
        String jscode2sessionUrl = weixinProperties.getJscode2sessionUrl()+"?appid="+weixinProperties.getAppid()+
                "&secret="+weixinProperties.getSecret()+"&js_code="+code+"&grant_type=authorization_code";
        System.out.println(jscode2sessionUrl);
        //请求获取openId
        String result = httpClientUtil.sendHttpGet(jscode2sessionUrl);
        JSONObject jsonObject = JSON.parseObject(result);
        String openId = jsonObject.get("openid").toString();
        System.out.println(openId);
        //插入用户到数据库，假如用户不存在 注册用户，如果存在跟新用户
        WxUser wxUser = wxUserService.findWxUserByOpenId(openId);
        ResponseResult responseResult;
        if (Objects.isNull(wxUser)){
            user.setOpenId(openId);
            responseResult = wxUserService.addWxUser(user);
        }else {
            user.setOpenId(openId);
            responseResult = wxUserService.UpdataWxUser(user);
        }

        return responseResult;
    }
}
