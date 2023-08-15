package cn.southwest.shop.constant;


import java.security.PublicKey;

/**
 * @Author：linan
 * @Date：2023/8/12 9:41
 */
public class SystemConstants {
    /**
     * 商品大类正常状态
     */
    public static final Integer BIG_TYPE_STATUS_NORMAL = 1;

    /**
     * 商品详情轮播图正常状态
     */
    public static final Integer PRODUCT_SWIPER_IMAGE_STATUS_NORMAL = 1;

    /**
     * 登录成功返回Code
     */
    public static final String LOGIN_SUCCESS_CODE = "success";

    /**
     * 操作成功返回Code
     */
    public static final String OPERATION_SUCCESS_CODE = "SUCCESS";

    /**
     * 操作失败返回Code
     */
    public static final String OPERATION_FAILURE_CODE = "ERROR";

    /**
     * 商品库存不足返回Code
     */
    public static final String PRODUCT_STOCK_LESS = "PRODUCT_STOCK_LESS";

    /**
     * 微信用户正常状态
     */
    public static final Integer USER_STATUS_NORMAL = 1;

    /**
     * 微信用户状态异常
     */
    public static final String USER_STATUS_EXCEPTION = "USER_STATUS_EXCEPTION";

    /**
     * 购物车正常状态
     */
    public static final Integer CART_STATUS_NORMAL = 1;

    /**
     * 购物车异常返回Code
     */
    public static final String CART_STATUS_EXCEPTION = "CART_STATUS_EXCEPTION";

    /**
     * 购物车为空
     */
    public static final String CART_NULL = "CART_NULL";

    /**
     * 购物车异常状态
     */
    public static final Integer CART_STATUS_ABNORMAL = 0;

    /**
     * 购物车商品状态正常
     */
    public static final Integer PRODUCT_CART_NORMAL = 1;

    /**
     * 购物车商品状态删除
     */
    public static final Integer PRODUCT_CART_ABNORMAL = 0;
}
