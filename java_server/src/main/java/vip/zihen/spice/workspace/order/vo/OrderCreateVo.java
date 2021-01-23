package vip.zihen.spice.workspace.order.vo;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateVo {

    /**
     * 收货地址
     */
    @NotNull
    private Integer addressId;
    /**
     * 子订单列表
     */
    @NotNull
    private List<OrderItemCreateVo> items;

    @Data
    public class OrderItemCreateVo {
        /**
         * 商品ID
         */
        @NotNull
        private Integer productId;
        /**
         * skuId
         */
        private Integer skuId;
        /**
         * 数量
         */
        @NotNull
        private Integer total;
    }


}
