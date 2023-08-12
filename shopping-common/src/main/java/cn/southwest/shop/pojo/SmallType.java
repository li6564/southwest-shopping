package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * (SmallType)表实体类
 *
 * @author makejava
 * @since 2023-08-12 09:12:24
 */
@Data
@TableName("t_small_type")
public class SmallType implements Serializable {
    //编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    //商品小类
    private String name;
    //备注
    private String remark;
    //商品大类编号
    private Integer bigTypeId;
    //状态 1正常 0删除
    private Integer status;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //跟新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(select = false)
    private List<Product> productList;

}

