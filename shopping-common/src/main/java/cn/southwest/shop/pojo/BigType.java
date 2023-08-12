package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (BigType)表实体类
 *
 * @author makejava
 * @since 2023-08-11 19:58:04
 */
@Data
@TableName("t_big_type")
public class BigType implements Serializable {
    //编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    //商品大类名称
    private String name;
    //备注
    private String remark;
    //大类封面图片
    private String image;
    //状态  1 正常 0 删除
    private Integer status;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

