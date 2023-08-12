package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (WxUser)表实体类
 *
 * @author makejava
 * @since 2023-08-12 16:57:12
 */
@Data
@TableName("t_wx_user")
@Accessors(chain = true)
public class WxUser implements Serializable {
    //用户编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    //用户唯一标识
    private String openId;
    //用户昵称
    private String nickName;
    //用户头像图片的 URL
    private String avatarUrl;
    //注册日期
    @TableField(fill = FieldFill.INSERT)
    private Date registerDate;
    //最后登录日期
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastLoginDate;

    @TableField(select = false,exist = false)
    private String code;
}

