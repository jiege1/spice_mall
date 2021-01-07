package vip.zihen.spice.workspace.admin.controller;

import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.workspace.admin.entity.Admin;
import vip.zihen.spice.workspace.admin.service.AdminService;
import org.springframework.web.bind.annotation.*;
import vip.zihen.spice.workspace.admin.vo.LoginParamVo;
import vip.zihen.spice.common.api.ApiCode;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.config.auth.jwt.JwtUtils;
import vip.zihen.spice.workspace.user.entity.UserRole;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * (Admin)表控制层
 *
 * @author wangjie
 * @since 2021-01-05 18:10:21
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 登陆
     * @param loginParamVo
     * @return
     */
    @PostMapping("login")
    public ApiResult selectOne(@RequestBody LoginParamVo loginParamVo) {

        Admin query = new Admin();
        query.setUsername(loginParamVo.getUsername());

        Admin admin = adminService.selectOne(query);

        if (Objects.isNull(admin)) {
            return ApiResult.fail(ApiCode.LOGIN_USER_NOT_EXIST);
        }

        if (!admin.getPassword().equals(loginParamVo.getPassword()) ) {
            return ApiResult.fail(ApiCode.LOGIN_PASSWORD_EXCEPTION);
        }

        // 管理员权限
        String token = JwtUtils.createToken(admin.getId(), UserRole.ADMIN.getRole());
        return ApiResult.ok(token);
    }

    @LoginRequired(roles = {UserRole.ADMIN})
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable int id) {
        return ApiResult.ok(adminService.deleteById(id));
    }

}