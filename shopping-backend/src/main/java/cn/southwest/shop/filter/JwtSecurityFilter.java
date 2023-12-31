package cn.southwest.shop.filter;

import cn.southwest.shop.constant.RedisConstants;
import cn.southwest.shop.pojo.WxUser;
import cn.southwest.shop.utils.JwtUtil;
import cn.southwest.shop.utils.RedisCache;
import com.aliyun.oss.common.utils.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token
        String token = httpServletRequest.getHeader("Authorization");
        //判断客户端是否携带token，不携带则直接放行
        if (StringUtils.isNullOrEmpty(token)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析token获取id
        String openId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            openId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String key = RedisConstants.LOGIN+openId;
        WxUser userDetails = redisCache.getCacheObject(key);
        if (Objects.isNull(userDetails)){
            throw new RuntimeException("用户未登录");
        }
        //刷新redis用户缓存信息
        redisCache.setCacheObject(RedisConstants.LOGIN +openId,userDetails,RedisConstants.LOGIN_KEY_TTL, TimeUnit.HOURS);

        //将用户信息封装到SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
