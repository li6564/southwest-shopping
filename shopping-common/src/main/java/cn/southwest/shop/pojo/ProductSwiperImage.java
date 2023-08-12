package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (ProductSwiperImage)表实体类
 *
 * @author makejava
 * @since 2023-08-12 11:12:26
 */
@Data
@TableName("t_product_swiper_image")
public class ProductSwiperImage implements Serializable {
    //编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    //图片
    private String image;
    //排序
    private Integer sort;
    //所属商品id
    private Integer productid;
    //状态 1正常 0删除
    private Integer status;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //跟新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

