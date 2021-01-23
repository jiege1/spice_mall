package vip.zihen.spice.workspace.admin.vo;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginParamVo {

    @NotNull
    @ApiModelProperty(value = "登录账号", example = "18008080507")
    private String username;

    @NotNull
    @ApiModelProperty(value = "密码", example = "111111")
    private String password;

}
