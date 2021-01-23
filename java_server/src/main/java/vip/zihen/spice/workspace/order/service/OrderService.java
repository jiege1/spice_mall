package vip.zihen.spice.workspace.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.zihen.spice.workspace.address.entity.Address;
import vip.zihen.spice.workspace.order.entity.Order;
import vip.zihen.spice.workspace.order.vo.OrderCreateVo;
import vip.zihen.spice.workspace.order.vo.OrderVo;

import java.util.List;

/**
 * (Order)表服务接口
 *
 * @author wangjie
 * @since 2021-01-16 16:47:38
 */
public interface OrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order queryById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<OrderVo> queryPageList(Integer page, Integer size, OrderVo query);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Order insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Order update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    OrderVo create(OrderCreateVo orderCreateVo, Integer userId);

}