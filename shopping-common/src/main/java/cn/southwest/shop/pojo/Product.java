package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Product)表实体类
 *
 * @author makejava
 * @since 2023-08-11 16:43:35
 */
@Data
@TableName("t_product")
public class Product implements Serializable {
    //编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    //商品名称
    private String name;
    //商品价格
    private Double price;
    //库存
    private Integer stock;
    //商品图片
    private String proPic;
    //是否热门推荐商品
    private Boolean isHot;
    //是否轮播图片商品
    private Boolean isSwiper;
    //商品轮播图片
    private String swiperPic;
    //轮播排序
    private Integer swiperSort;
    //商品类别编号
    private Integer typeId;
    //设置热卖日期
    private Date hotDatetime;
    //商品介绍图片
    private String productIntroimgs;
    //商品规格参数图片
    private String productParaimgs;
    //商品描述
    private String description;
    //状态  1 正常 0 已售空
    private Integer status;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

