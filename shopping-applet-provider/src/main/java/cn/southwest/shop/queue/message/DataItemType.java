package cn.southwest.shop.queue.message;

import cn.southwest.shop.pojo.Cart;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author meteor
 */
@Getter
public enum DataItemType implements Serializable {

    /**
     * 购物车商品
     */
    CART_PRODUCT(Cart.class);


    private Class<?> classOfItem;

    DataItemType(Class<?> classOfItem) {
        this.classOfItem = classOfItem;
    }
}
