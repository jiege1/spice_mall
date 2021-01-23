package vip.zihen.spice.workspace.order.vo.bo;

import lombok.Data;

@Data
public class OrderItemCreateBo {

    /**
     * 订单ID
     */
    private Integer orderId;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 数量
     */
    private Integer total;
    /**
     * 选择的skuId
     */
    private Integer skuId;

}
