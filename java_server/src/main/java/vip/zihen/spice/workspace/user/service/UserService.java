package vip.zihen.spice.workspace.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.zihen.spice.workspace.user.entity.UserEntity;

import java.util.List;

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

    /**
     * 查询列表
     * @param page
     * @param size
     * @param keywords
     * @return
     */
    Page<UserEntity> queryPageList(int page, int size, String keywords);

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

    /**
     * 通过小程序openId获取用户信息
     * @param openId
     * @return
     */
    UserEntity queryByOpenId(String openId);

}