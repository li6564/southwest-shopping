package cn.southwest.shop.service.impl;

import cn.southwest.shop.api.ResponseResult;
import cn.southwest.shop.common.dto.PageDTO;
import cn.southwest.shop.mapper.ProductMapper;
import cn.southwest.shop.pojo.Product;
import cn.southwest.shop.pojo.ProductSwiperImage;
import cn.southwest.shop.service.IProductService;
import cn.southwest.shop.service.IProductSwiperImageService;
import cn.southwest.shop.utils.BeanCopyUtils;
import cn.southwest.shop.vo.ProductDetailVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author：linan
 * @Date：2023/8/11 16:06
 */
@Service
public class IProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements IProductService {

    @Reference
    private IProductSwiperImageService productSwiperImageService;

    @Override
    public ResponseResult findSwiper() {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getIsSwiper,true)
                .orderByAsc(Product::getSwiperSort);
        List<Product> swiperProductList = list(queryWrapper);
        System.out.println(swiperProductList);
        return new ResponseResult(swiperProductList);
    }

    @Override
    public ResponseResult findHot() {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getIsHot,true)
                .eq(Product::getStatus,1)
                .orderByDesc(Product::getHotDatetime);
        //分页查询
        Page<Product> page = new Page<>(0,8);
        page(page,queryWrapper);
        //分页封装
        PageDTO<Product> pageDTO = new PageDTO<>();
        pageDTO.setRecords(page.getRecords());

        return new ResponseResult(pageDTO);
    }

    @Override
    public List<Product> findProductBySmallTypeId(Integer smallTypeId) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getTypeId,smallTypeId)
                .eq(Product::getStatus,1);
        List<Product> productList = list(queryWrapper);
        return productList;
    }

    @Override
    public ResponseResult findProductById(Integer productId) {
        //根据id获取商品
        Product product = getById(productId);
        //根据商品id获取商品详情轮播图
        List<ProductSwiperImage> productSwiperImageList = productSwiperImageService.findProductSwiperImageByProductId(productId);
        //封装返回对象productDetailVo
        ProductDetailVo productDetailVo = new ProductDetailVo();
        ProductDetailVo productDetail = BeanCopyUtils.copyBean(product, productDetailVo.getClass());
        //填充商品详情轮播图
        productDetail.setProductSwiperImageList(productSwiperImageList);
        return new ResponseResult(productDetail);
    }
}
