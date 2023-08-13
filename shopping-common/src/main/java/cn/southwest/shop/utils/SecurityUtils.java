package cn.southwest.shop.utils;


import cn.southwest.shop.pojo.WxUser;
import com.aliyuncs.utils.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author
 */
public class SecurityUtils
{

    /**
     * 获取用户
     **/
    public static WxUser getLoginUser()
    {
        return (WxUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        String id = getLoginUser().getOpenId();
        return StringUtils.isNotEmpty(id);
    }

    /**
     * 判断是否登录
     * @return
     */
    public static Boolean isLogin(){
        return !"anonymousUser".equals(getAuthentication().getPrincipal());
    }

    /**
     * 获取登录用户id
     * @return
     */
    public static String getUserId() {
        return getLoginUser().getOpenId();
    }
}