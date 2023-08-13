package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (Cart)表实体类
 *
 * @author makejava
 * @since 2023-08-13 14:34:04
 */
@Data
@TableName("t_cart")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    //购物车Id
    @TableId(type = IdType.AUTO)
    private Integer cartId;
    //用户openId
    private String userId;
    //产品数量
    private Integer number;
    //购物车状态 1正常 0 异常
    private Integer status;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //跟新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

