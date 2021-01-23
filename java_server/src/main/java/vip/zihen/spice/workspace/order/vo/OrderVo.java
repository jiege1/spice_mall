package vip.zihen.spice.workspace.order.vo;

import lombok.Data;
import vip.zihen.spice.workspace.order.entity.Order;
import vip.zihen.spice.workspace.order.entity.OrderItem;

import java.util.List;

@Data
public class OrderVo extends Order {

    private List<OrderItem> orderItems;

}
