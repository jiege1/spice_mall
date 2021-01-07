package vip.zihen.spice.workspace.user.service;

import vip.zihen.spice.workspace.user.entity.UserEntity;

/**
 * (UserEntity)表服务接口
 *
 * @author wangjie
 * @since 2021-01-04 19:20:55
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserEntity queryById(Integer id);

//    /**
//     * 查询多条数据
//     *
//     * @param offset 查询起始位置
//     * @param limit 查询条数
//     * @return 对象列表
//     */
//    List<UserEntity> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    UserEntity insert(UserEntity userEntity);

    /**
     * 修改数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    UserEntity update(UserEntity userEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}