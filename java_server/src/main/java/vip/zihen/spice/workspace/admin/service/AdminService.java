package vip.zihen.spice.workspace.admin.service;

import vip.zihen.spice.workspace.admin.entity.Admin;

/**
 * (Admin)表服务接口
 *
 * @author wangjie
 * @since 2021-01-05 18:10:19
 */
public interface AdminService {

    Admin selectOne(Admin query);

    Admin selectById(Integer id);

    boolean deleteById(int id);

}