package vip.zihen.spice.workspace.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import vip.zihen.spice.workspace.admin.entity.Admin;
import vip.zihen.spice.workspace.admin.mapper.AdminMapper;
import vip.zihen.spice.workspace.admin.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Admin)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-05 18:10:20
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper mapper;

    @Override
    public Admin selectById(Integer id) {
        return null;
    }

    @Override
    public Admin selectOne(Admin query) {
        return mapper.selectOne(new QueryWrapper<Admin>(query));
    }

    @Override
    public boolean deleteById(int id) {
        return mapper.deleteById(id) > 0;
    }
}