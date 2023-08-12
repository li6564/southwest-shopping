package cn.southwest.shop.vo;

import cn.southwest.shop.pojo.ProductSwiperImage;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/12 11:04
 */
@Data
public class ProductDetailVo implements Serializable {
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
    private String productIntroImgs;
    //商品规格参数图片
    private String productParaImgs;
    //商品描述
    private String description;
    //状态  1 正常 0 下架
    private Integer status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    private List<ProductSwiperImage> productSwiperImageList;
}
