package vip.zihen.spice.workspace.address.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (Address)实体类
 *
 * @author wangjie
 * @since 2021-01-18 10:35:05
 */
@Data
public class Address extends BaseEntity {

    /**
    * 是否默认（0:否，1:是）
    */
    private Boolean isDefault;
    /**
    * 省
    */
    private String province;
    /**
    * 市
    */
    private String city;
    /**
    * 县
    */
    private String area;
    /**
    * 详细地址
    */
    private String address;
    /**
    * 联系电话
    */
    private String tel;
    /**
    * 收货人名称
    */
    private String name;

}