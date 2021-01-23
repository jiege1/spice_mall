package vip.zihen.spice.workspace.order.service.impl;

import org.springframework.stereotype.Service;
import vip.zihen.spice.workspace.order.entity.OrderItem;
import vip.zihen.spice.workspace.order.mapper.OrderItemMapper;
import vip.zihen.spice.workspace.order.service.OrderItemService;
import vip.zihen.spice.workspace.order.vo.bo.OrderItemCreateBo;
import vip.zihen.spice.workspace.product.entity.Product;
import vip.zihen.spice.workspace.product.entity.Sku;
import vip.zihen.spice.workspace.product.service.ProductService;
import vip.zihen.spice.workspace.product.service.SkuService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * (OrderItem)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-16 16:52:02
 */
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

    @Resource private OrderItemMapper orderItemMapper;
    @Resource private ProductService productService;
    @Resource private SkuService skuService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderItem queryById(Integer id) {
        return orderItemMapper.selectById(id);
    }


    /**
     * 新增数据
     *
     * @param orderItem 实例对象
     * @return 实例对象
     */
    @Override
    public OrderItem insert(OrderItem orderItem) {
        int id = orderItemMapper.insert(orderItem);
        orderItem.setId(id);
        return orderItem;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return orderItemMapper.deleteById(id) > 0;
    }

    /**
     * 批量插入
     * @param orderItems
     * @return
     */
    @Override
    public boolean insertBatch(List<OrderItem> orderItems) {

        return false;
    }

    /**
     * 根据orderId获取子订单列表
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> queryListByOrderId(Integer orderId) {
        return null;
    }

    /**
     * 根据 orderIds 获取 所包含的子订单列表
     * @param orderIds
     * @return
     */
    @Override
    public List<OrderItem> queryListByOrderIds(List<Integer> orderIds) {
        return null;
    }

    /**
     * 创建子订单
     * @param orderItemCreateBo
     * @return
     */
    @Override
    public OrderItem create(OrderItemCreateBo orderItemCreateBo) {

        OrderItem orderItem = new OrderItem();

        // 数量
        orderItem.setTotal(orderItemCreateBo.getTotal());

        // 记录商品
        Product product = productService.queryById(orderItemCreateBo.getProductId());
        if (Objects.isNull(product)) {
            // todo: 验证商品是否存在
        }
        orderItem.setProductId(product.getId());
        orderItem.setProductImage(product.getImage().split(",")[0]);
        orderItem.setProductPrice(product.getPrice());
        orderItem.setProductTitle(product.getTitle());

        // 记录sku信息
        Integer skuId = orderItemCreateBo.getSkuId();
        if (!Objects.isNull(skuId)) {
            Sku sku = skuService.queryById(skuId);
            if (!Objects.isNull(sku)) {
                orderItem.setSkuId(orderItemCreateBo.getSkuId());
                orderItem.setSkuTitle(sku.getTitle());
            }
        }


        return null;
    }

    /**
     * 批量创建子订单
     * @param orderItemCreateBos
     * @return
     */
    @Override
    public List<OrderItem> createBatch(List<OrderItemCreateBo> orderItemCreateBos) {
        return null;
    }
}