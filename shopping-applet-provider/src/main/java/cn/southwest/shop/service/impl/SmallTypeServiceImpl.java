package cn.southwest.shop.service.impl;

import cn.southwest.shop.mapper.SmallTypeMapper;
import cn.southwest.shop.pojo.Product;
import cn.southwest.shop.pojo.SmallType;
import cn.southwest.shop.service.IBigTypeService;
import cn.southwest.shop.service.IProductService;
import cn.southwest.shop.service.ISmallTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/12 9:22
 */
@Service
public class SmallTypeServiceImpl extends ServiceImpl<SmallTypeMapper, SmallType> implements ISmallTypeService {

    @Reference
    private IProductService productService;

    @Override
    public List<SmallType> findSmallTypeByBigTypeId(Integer bigTypeId) {
        //根据商品大类id查询商品小类
        LambdaQueryWrapper<SmallType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SmallType::getBigTypeId,bigTypeId)
                .eq(SmallType::getStatus,1);
        List<SmallType> smallTypeList = list(queryWrapper);
        //在商品小类中封装商品信息
        smallTypeList.forEach( smallType -> {
            //根据商品小类id查询商品信息
            List<Product> productList = productService.findProductBySmallTypeId(smallType.getId());
            smallType.setProductList(productList);
        });
        return smallTypeList;
    }
}
