package cn.southwest.shop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (Admin)表实体类
 *
 * @author makejava
 * @since 2023-08-11 12:13:31
 */
@Data
@TableName("t_admin")
public class Admin implements Serializable{

    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String username;
    
    private String password;

}

