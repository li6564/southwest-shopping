package cn.southwest.shop.listener.impl;

import cn.southwest.shop.listener.DataItemChangeListener;
import cn.southwest.shop.pojo.Cart;
import cn.southwest.shop.queue.message.DataItemChangeMessage;
import cn.southwest.shop.queue.message.DataItemChangeType;
import cn.southwest.shop.queue.message.DataItemType;
import cn.southwest.shop.service.ICartService;
import lombok.Data;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.yetus.audience.InterfaceAudience;
import org.springframework.stereotype.Component;

/**
 * @Author：linan
 * @Date：2023/8/15 9:51
 */
@Data
@Component
public class CartProductChangeListener implements DataItemChangeListener {

    @Reference
    private ICartService cartService;

    @Override
    public void onDataItemAdd(DataItemChangeMessage dataItemChangeMessage) throws Exception {
        DataItemType itemType = dataItemChangeMessage.getItemType();
        if (itemType.equals(DataItemType.CART_PRODUCT)){
            onDataChange(dataItemChangeMessage);
        }
    }

    @Override
    public void onDataItemDelete(DataItemChangeMessage dataItemChangeMessage) throws Exception {
        DataItemType itemType = dataItemChangeMessage.getItemType();
        if (itemType.equals(DataItemType.CART_PRODUCT)){
            onDataChange(dataItemChangeMessage);
        }
    }

    public void onDataChange(DataItemChangeMessage dataItemChangeMessage){
        Cart cart = cartService.getById(dataItemChangeMessage.getItemId());
        //购物车商品数量+1或-1
        if (dataItemChangeMessage.getChangeType().equals(DataItemChangeType.ADD)){
            cart.setNumber(cart.getNumber()+1);
            cartService.updateById(cart);
        }else if (dataItemChangeMessage.getChangeType().equals(DataItemChangeType.DELETE)){
            cart.setNumber(cart.getNumber()-1);
            cartService.updateById(cart);
        }
        System.out.println(123);
    }
}
