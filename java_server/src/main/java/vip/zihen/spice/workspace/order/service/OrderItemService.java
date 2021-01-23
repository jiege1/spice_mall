package vip.zihen.spice.workspace.order.service;

import vip.zihen.spice.workspace.order.entity.OrderItem;
import vip.zihen.spice.workspace.order.vo.bo.OrderItemCreateBo;

import java.util.List;

/**
 * (OrderItem)表服务接口
 *
 * @author wangjie
 * @since 2021-01-16 16:52:02
 */
public interface OrderItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderItem queryById(Integer id);

    /**
     * 新增数据
     *
     * @param orderItem 实例对象
     * @return 实例对象
     */
    OrderItem insert(OrderItem orderItem);

    /**
     * 批量新增
     * @param orderItems
     * @return
     */
    boolean insertBatch(List<OrderItem> orderItems);

    /**
     * 创建单个子订单
     * @param orderItemCreateBo
     * @return
     */
    OrderItem create(OrderItemCreateBo orderItemCreateBo);

    /**
     * 批量创建某个订单下的所有子订单
     * @param orderItemCreateBos
     * @return
     */
    List<OrderItem> createBatch(List<OrderItemCreateBo> orderItemCreateBos);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询某个订单下的所有子订单
     * @param orderId
     * @return
     */
    List<OrderItem> queryListByOrderId(Integer orderId);

    /**
     * 查询某几个订单下的所有子订单
     * @param orderIds
     * @return
     */
    List<OrderItem> queryListByOrderIds(List<Integer> orderIds);

}