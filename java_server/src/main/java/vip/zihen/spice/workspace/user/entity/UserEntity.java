package vip.zihen.spice.workspace.user.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (UserEntity)实体类
 *
 * @author wangjie
 * @since 2021-01-04 19:20:54
 */
@Data
public class UserEntity extends BaseEntity {

    /**
    * 名字
    */
    private String name;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 年龄
    */
    private Integer age;
    /**
    * 省ID
    */
    private String province;
    /**
    * 市ID
    */
    private String city;
    /**
    * 县ID
    */
    private String area;
    /**
    * 详细地址
    */
    private String address;
    /**
    * 1:man,2:woman,3:unknow
    */
    private Integer sex;
    /**
    * 0:caller,1:admin,2:user,
    */
    private Integer role;
    /**
    * 微信openId 
    */
    private String openId;
    /**
    * 管理员ID
    */
    private Integer adminId;
    /**
    * 微信头像
    */
    private String avatar;
}