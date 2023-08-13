package cn.southwest.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：linan
 * @Date：2023/8/13 18:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartProductVo implements Serializable {
    //编号
    private Integer id;
    //商品名称
    private String name;
    //商品价格
    private Double price;
    //库存
    private Integer stock;
    //商品图片
    private String proPic;
    //状态  1 正常 0 下架
    private Integer status;
    //购物车中商品的数量
    private Integer num;
}
