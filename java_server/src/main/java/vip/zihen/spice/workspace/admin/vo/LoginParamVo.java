package vip.zihen.spice.workspace.admin.vo;

import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class LoginParamVo {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
