package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (Address)表实体类
 *
 * @author makejava
 * @since 2023-08-14 10:00:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_address")
public class Address  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //用户 openid
    private String openId;
    //邮政编码
    private String nationalCode;
    //联系电话
    private String telNumber;
    //省份
    private String provinceName;
    //城市
    private String cityName;
    //区县
    private String countyName;
    //详细地址
    private String detailInfo;
    //是否默认地址 1是 0否
    private Integer isDefault;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //跟新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

