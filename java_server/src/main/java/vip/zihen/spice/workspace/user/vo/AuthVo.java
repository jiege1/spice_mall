package vip.zihen.spice.workspace.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthVo {

    @ApiModelProperty(value = "用户昵称", example = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像", example = "image")
    private String avatar;

    @ApiModelProperty(value = "省", example = "Sichuan")
    private String province;

    @ApiModelProperty(value = "市", example = "Chengdu")
    private String city;
}
