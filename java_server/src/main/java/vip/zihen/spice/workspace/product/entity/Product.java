package vip.zihen.spice.workspace.product.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (Product)实体类
 *
 * @author wangjie
 * @since 2021-01-06 11:14:01
 */
@Data
public class Product extends BaseEntity {
    /**
    * 标题
    */
    private String title;
    /**
    * 价格
    */
    private Integer price;
    /**
    * 商品主图
    */
    private String image;
    /**
    * status(0:下架，1:上架)
    */
    private Integer status;
    /**
    * 商品详情图片
    */
    private String details;
    /**
    * 销量
    */
    private Integer sales;
    /**
    * 虚拟销量
    */
    private Integer virtualSales;
    /**
    * 库存
    */
    private Integer stock;

}