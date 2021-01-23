package vip.zihen.spice.workspace.address.controller;

import vip.zihen.spice.workspace.address.entity.Address;
import vip.zihen.spice.workspace.address.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Address)表控制层
 *
 * @author wangjie
 * @since 2021-01-18 10:29:19
 */
@RestController
@RequestMapping("address")
public class AddressController {
    /**
     * 服务对象
     */
    @Resource
    private AddressService addressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Address selectOne(Integer id) {
        return this.addressService.queryById(id);
    }

}