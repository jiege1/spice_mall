package vip.zihen.spice.config.auth.jwt;

import lombok.Data;

import java.util.Date;

@Data
public class JwtToken {

    private String host;
    /**
     * 登录用户名称
     */
    private String username;
    /**
     * 登录用户名称
     */
    private Integer userId;
    /**
     * 登录用户名称
     */
    private String openId;
    /**
     * 登录盐值
     */
    private String salt;
    /**
     * 登录token
     */
    private String token;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 多长时间过期，默认一小时
     */
    private long expireSecond;
    /**
     * 过期日期
     */
    private Date expireDate;

//    private String principal;
//
//    private String credentials;

}
