package vip.zihen.spice.workspace.order.controller.mall;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.common.utils.RequestUtils;
import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.workspace.address.entity.Address;
import vip.zihen.spice.workspace.address.service.AddressService;
import vip.zihen.spice.workspace.order.entity.Order;
import vip.zihen.spice.workspace.order.service.OrderItemService;
import vip.zihen.spice.workspace.order.service.OrderService;
import vip.zihen.spice.workspace.order.vo.OrderCreateVo;
import vip.zihen.spice.workspace.order.vo.OrderVo;
import vip.zihen.spice.workspace.order.vo.bo.OrderItemCreateBo;
import vip.zihen.spice.workspace.user.entity.UserRole;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author wangjie
 * @since 2021-01-16 16:47:42
 */
@Api("C端订单模块")
@RestController
@RequestMapping("c/order")
public class MallOrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private AddressService addressService;

    /**
     * 创建订单
     * @param createVo
     * @return
     */
    @ApiOperation("创建订单")
    @LoginRequired(roles = { UserRole.USER, UserRole.ADMIN })
    @PostMapping
    public ApiResult<OrderVo> create(HttpServletRequest request,
                                     @RequestBody OrderCreateVo createVo) {
        return ApiResult.ok(
                orderService.create(createVo, RequestUtils.getUserId(request))
        );
    }

}
