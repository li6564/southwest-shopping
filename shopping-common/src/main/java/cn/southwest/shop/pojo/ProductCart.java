package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (ProductCart)表实体类
 *
 * @author makejava
 * @since 2023-08-13 14:34:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_product_cart")
public class ProductCart implements Serializable {
    //商品购物车Id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //商品id
    private Integer productId;
    //购物车id
    private Integer cartId;
    //状态 1商品存在购物车里 0商品不存在购物车
    private Integer status;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //跟新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

