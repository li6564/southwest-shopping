package cn.southwest.shop.mapper;

import cn.southwest.shop.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author：linan
 * @Date：2023/8/11 12:20
 */
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from t_admin where id = #{id}")
    public Admin findById(Integer id);
}
