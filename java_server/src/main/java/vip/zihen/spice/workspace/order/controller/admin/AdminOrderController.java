package vip.zihen.spice.workspace.order.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.tools.corba.se.idl.constExpr.Or;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.common.utils.CommonUtils;
import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.workspace.order.entity.Order;
import vip.zihen.spice.workspace.order.entity.OrderItem;
import vip.zihen.spice.workspace.order.service.OrderItemService;
import vip.zihen.spice.workspace.order.service.OrderService;
import org.springframework.web.bind.annotation.*;
import vip.zihen.spice.workspace.order.vo.OrderCreateVo;
import vip.zihen.spice.workspace.order.vo.OrderVo;
import vip.zihen.spice.workspace.user.entity.UserRole;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Order)表控制层
 *
 * @author wangjie
 * @since 2021-01-16 16:47:42
 */
@Api("B端订单模块")
@RestController
@RequestMapping("b/order")
public class AdminOrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService orderItemService;


    @ApiOperation("查询订单列表")
    @LoginRequired(roles = { UserRole.ADMIN })
    @GetMapping()
    public ApiResult<Page<OrderVo>> selectPage(@RequestParam Integer page,
                                               @RequestParam Integer size) {
        return ApiResult.ok(orderService.queryPageList(page, size, null));
    }

    /**
     * 根据ID查询单条订单数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("根据ID查询单条订单数据")
    @LoginRequired(roles = { UserRole.ADMIN })
    @GetMapping("/{id}")
    public ApiResult<OrderVo> selectOne(@PathVariable Integer id) {
        List<OrderItem> orderItems = orderItemService.queryListByOrderId(id);
        Order order = orderService.queryById(id);

        OrderVo orderVo = CommonUtils.convertData(order, OrderVo.class);
        orderVo.setOrderItems(orderItems);

        return ApiResult.ok(orderVo);
    }

}