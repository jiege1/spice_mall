package vip.zihen.spice.config.auth.jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUser {

    private Integer userId;

    private String openId;

    private String username;

    private int role;

}
