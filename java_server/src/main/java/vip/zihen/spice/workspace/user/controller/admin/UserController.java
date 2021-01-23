package vip.zihen.spice.workspace.user.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.zihen.spice.common.api.ApiCode;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.workspace.user.entity.UserEntity;
import vip.zihen.spice.workspace.user.service.UserService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * (UserEntity)表控制层
 *
 * @author wangjie
 * @since 2021-01-04 19:21:01
 */
@Api(tags = "B端用户模块")
@RestController("adminUserController")
@RequestMapping("b/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("查询单个用户")
    @LoginRequired
    @GetMapping("/{id}")
    public ApiResult selectOne(@PathVariable Integer id) {
        UserEntity userEntity = userService.queryById(id);
        if (Objects.isNull(userEntity)) {
            return ApiResult.fail(ApiCode.NOT_FOUND);
        }
        return ApiResult.ok(userEntity);
    }

}