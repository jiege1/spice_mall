package vip.zihen.spice.workspace.order.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

import java.util.Date;

/**
 * (Order)实体类
 *
 * @author wangjie
 * @since 2021-01-16 16:47:33
 */
@Data
public class Order extends BaseEntity {

    /**
    * 订单总价
    */
    private Integer totalMoney;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 收货地址
    */
    private Integer addId;
    /**
    * 收货地址的省
    */
    private String addProvince;
    /**
    * 收货地址的市
    */
    private String addCity;
    /**
    * 收货地址的县
    */
    private String addArea;
    /**
    * 收货地址的详细地址
    */
    private String addDetail;
    /**
    * 订单状态
    */
    private Integer status;

}