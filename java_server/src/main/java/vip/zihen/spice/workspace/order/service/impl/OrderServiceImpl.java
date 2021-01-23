package vip.zihen.spice.workspace.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import vip.zihen.spice.common.utils.CommonUtils;
import vip.zihen.spice.workspace.address.entity.Address;
import vip.zihen.spice.workspace.address.service.AddressService;
import vip.zihen.spice.workspace.order.entity.Order;
import vip.zihen.spice.workspace.order.entity.OrderItem;
import vip.zihen.spice.workspace.order.mapper.OrderMapper;
import vip.zihen.spice.workspace.order.service.OrderItemService;
import vip.zihen.spice.workspace.order.service.OrderService;
import vip.zihen.spice.workspace.order.vo.OrderCreateVo;
import vip.zihen.spice.workspace.order.vo.OrderVo;
import vip.zihen.spice.workspace.order.vo.bo.OrderItemCreateBo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Order)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-16 16:47:40
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource private OrderMapper orderMapper;
    @Resource private AddressService addressService;
    @Resource private OrderItemService orderItemService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public Page<OrderVo> queryPageList(Integer page, Integer size, OrderVo query) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order insert(Order order) {
        int id = orderMapper.insert(order);
        order.setId(id);
        return order;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order update(Order order) {
        orderMapper.update(order, new UpdateWrapper<Order>(order));
        return queryById(order.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return orderMapper.deleteById(id) > 0;
    }

    /**
     * 创建订单
     * @param orderCreateVo
     * @param userId
     * @return
     */
    @Override
    public OrderVo create(OrderCreateVo orderCreateVo, Integer userId) {
        Address address = addressService.queryById(orderCreateVo.getAddressId());

        Order order = new Order();

        order.setUserId(userId);
        order.setAddId(orderCreateVo.getAddressId());
        order.setAddArea(address.getArea());
        order.setAddCity(address.getCity());
        order.setAddDetail(address.getCity());
        order.setAddProvince(address.getProvince());

        order = this.insert(order);

        Integer orderId = order.getId();

        /**
         * 创建子订单
         */
        List<OrderCreateVo.OrderItemCreateVo> items = orderCreateVo.getItems();
        List<OrderItemCreateBo> orderItemCreateBos = items.stream()
                .map(item -> {
                    OrderItemCreateBo orderItemCreateBo = new OrderItemCreateBo();
                    orderItemCreateBo.setOrderId(orderId);
                    orderItemCreateBo.setProductId(item.getProductId());
                    orderItemCreateBo.setTotal(item.getTotal());
                    orderItemCreateBo.setSkuId(item.getSkuId());
                    return orderItemCreateBo;
                })
                .collect(Collectors.toList());
        List<OrderItem> orderItems = orderItemService.createBatch(orderItemCreateBos);

        OrderVo orderVo = CommonUtils.convertData(order, OrderVo.class);
        orderVo.setOrderItems(orderItems);

        return orderVo;
    }
}