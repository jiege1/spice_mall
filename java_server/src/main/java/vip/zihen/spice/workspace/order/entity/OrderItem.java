package vip.zihen.spice.workspace.order.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (OrderItem)实体类
 *
 * @author wangjie
 * @since 2021-01-16 16:52:02
 */
@Data
public class OrderItem extends BaseEntity {

    /**
    * 商品ID
    */
    private Integer productId;
    /**
    * 选择的skuId
    */
    private Integer skuId;
    /**
    * 商品标题快照
    */
    private String productTitle;
    /**
    * 商品主图快照
    */
    private String productImage;
    /**
    * 商品价格快照
    */
    private Integer productPrice;
    /**
    * 选择的sku配置
    */
    private String skuTitle;
    /**
    * 下单的数量
    */
    private Integer total;
    /**
    * 子订单价格
    */
    private Integer money;
    /**
    * 所属订单ID
    */
    private Integer orderId;

}