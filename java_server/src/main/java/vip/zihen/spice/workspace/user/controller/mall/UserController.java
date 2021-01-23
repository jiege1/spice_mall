package vip.zihen.spice.workspace.user.controller.mall;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;
import vip.zihen.spice.common.api.ApiCode;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.common.utils.RequestUtils;
import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.config.auth.jwt.JwtUtils;
import vip.zihen.spice.config.wx.WxMaConfiguration;
import vip.zihen.spice.workspace.admin.entity.Admin;
import vip.zihen.spice.workspace.admin.service.AdminService;
import vip.zihen.spice.workspace.admin.vo.LoginParamVo;
import vip.zihen.spice.workspace.user.entity.UserEntity;
import vip.zihen.spice.workspace.user.entity.UserRole;
import vip.zihen.spice.workspace.user.service.UserService;
import vip.zihen.spice.workspace.user.vo.AuthVo;
import vip.zihen.spice.workspace.user.vo.JsCodeVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Api(tags = "C端用户模块")
@RestController("mallUserController")
@RequestMapping("c/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;

    /**
     * 小程序登陆
     * @param jsCodeVo
     * @return
     */
    @ApiOperation("小程序登陆")
    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody JsCodeVo jsCodeVo) {
        String jsCode = jsCodeVo.getJsCode();

        final WxMaService maServices = WxMaConfiguration.getMaServices();
        try {
            WxMaJscode2SessionResult session = maServices.getUserService().getSessionInfo(jsCode);

//            String sessionKey = session.getSessionKey();
            String openId = session.getOpenid();
            UserEntity user = userService.queryByOpenId(openId);

            /**
             * 如果用户不存在，则新增用户
             * 新增的用户，默认为 访客。
             */
            if (Objects.isNull(user)) {
                UserEntity newUser = new UserEntity();
                newUser.setOpenId(openId);
                newUser.setRole(UserRole.CALLER.getRole());
                user = userService.insert(newUser);
            }

            // 生成token
            String token = JwtUtils.createToken(user.getId(), user.getOpenId(), user.getRole());

            return ApiResult.ok(token);
        } catch (WxErrorException e) {
            return ApiResult.fail(e.getMessage());
        }
    }

    /**
     * 获取登陆用户详细
     * @param request
     * @return
     */
    @ApiOperation("获取登陆用户详细")
    @LoginRequired(roles = { UserRole.USER, UserRole.ADMIN })
    @GetMapping("/info")
    public ApiResult<UserEntity> getUserInfo(HttpServletRequest request) {
        Integer id = RequestUtils.getUserId(request);
        return ApiResult.ok(userService.queryById(id));
    }

    /**
     * 用户授权后，用户角色由 访客 升级成 客户
     * @param request
     * @param authVo
     * @return
     */
    @ApiOperation("用户授权后，用户角色由 访客 升级成 客户")
    @LoginRequired(roles = { UserRole.CALLER })
    @PostMapping("/auth")
    public ApiResult<UserEntity> userAuth(HttpServletRequest request, @RequestBody AuthVo authVo) {
        Integer userId = RequestUtils.getUserId(request);

        UserEntity updateUser = new UserEntity();

        updateUser.setId(userId);
        updateUser.setAvatar(authVo.getAvatar());
        updateUser.setNickname(authVo.getNickname());
        updateUser.setRole(UserRole.USER.getRole());
        // todo：转化省市

        return ApiResult.ok(userService.update(updateUser));
    }

    /**
     * C端用户登陆升级为 系统管理员
     * @param request
     * @param loginParamVo
     * @return
     * @throws Exception
     */
    @ApiOperation("C端用户登陆升级为 系统管理员")
    @LoginRequired(roles = { UserRole.USER })
    @PostMapping("/admin/login")
    public ApiResult<UserEntity> userToAdmin(HttpServletRequest request,
                                             @RequestBody LoginParamVo loginParamVo) throws Exception {
        Integer userId = RequestUtils.getUserId(request);

        Admin admin = adminService.adminLogin(loginParamVo.getUsername(), loginParamVo.getPassword());

        UserEntity updateUser = new UserEntity();
        updateUser.setId(userId);
        updateUser.setAdminId(admin.getId());

        return ApiResult.ok(userService.update(updateUser));
    }

}
