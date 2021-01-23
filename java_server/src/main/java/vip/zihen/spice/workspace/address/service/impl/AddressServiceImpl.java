package vip.zihen.spice.workspace.address.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import vip.zihen.spice.workspace.address.entity.Address;
import vip.zihen.spice.workspace.address.mapper.AddressMapper;
import vip.zihen.spice.workspace.address.service.AddressService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Address)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-18 10:29:19
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Address queryById(Integer id) {
        return addressMapper.selectById(id);
    }

//    /**
//     * 查询多条数据
//     *
//     * @param offset 查询起始位置
//     * @param limit 查询条数
//     * @return 对象列表
//     */
//    @Override
//    public List<Address> queryAllByLimit(int offset, int limit) {
//        return addressMapper.selectPage(offset, limit);
//    }

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public Address insert(Address address) {
        int id = addressMapper.insert(address);
        address.setId(id);
        return address;
    }

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public Address update(Address address) {
        addressMapper.update(address, new UpdateWrapper<Address>(address));
        return queryById(address.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return addressMapper.deleteById(id) > 0;
    }
}