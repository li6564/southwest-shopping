package cn.southwest.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/13 18:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CartVo implements Serializable {
    private List<CartProductVo> carProductVoList;
}
