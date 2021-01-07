package vip.zihen.spice.workspace.product.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * (Sku)实体类
 *
 * @author wangjie
 * @since 2021-01-06 19:36:11
 */
@Data
public class Sku extends BaseEntity {
    private static final long serialVersionUID = 878210278010731449L;
    
    private Integer id;
    /**
    * skuProp集合
    */
    private String properties;
    /**
    * 价格
    */
    private Integer price;
    /**
    * 库存
    */
    private Integer stock;
    /**
     * 商品ID
     */
    private Integer productId;

}