package vip.zihen.spice.workspace.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import vip.zihen.spice.workspace.user.entity.UserEntity;
import vip.zihen.spice.workspace.user.mapper.UserMapper;
import vip.zihen.spice.workspace.user.service.UserService;

import javax.annotation.Resource;

/**
 * (UserEntity)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-04 19:21:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserEntity queryById(Integer id) {
        return mapper.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
//    @Override
//    public List<UserEntity> queryAllByLimit(int offset, int limit) {
//        return mapper.selectPage();
//    }

    /**
     * 新增数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    @Override
    public UserEntity insert(UserEntity userEntity) {
        int id = mapper.insert(userEntity);
        userEntity.setId(id);
        return userEntity;
    }

    /**
     * 修改数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    @Override
    public UserEntity update(UserEntity userEntity) {
        mapper.update(userEntity, new UpdateWrapper<UserEntity>(userEntity));
        return queryById(userEntity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return mapper.deleteById(id) > 0;
    }
}