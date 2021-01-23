package vip.zihen.spice.workspace.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import vip.zihen.spice.common.api.ApiCode;
import vip.zihen.spice.common.exception.BusinessException;
import vip.zihen.spice.workspace.admin.entity.Admin;
import vip.zihen.spice.workspace.admin.mapper.AdminMapper;
import vip.zihen.spice.workspace.admin.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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

    @Override
    public Admin adminLogin(String username, String password) throws BusinessException {

        Admin query = new Admin();
        query.setUsername(username);

        Admin admin = this.selectOne(query);

        if (Objects.isNull(admin)) {
            throw new BusinessException(ApiCode.LOGIN_USER_NOT_EXIST);
        }

        if (!admin.getPassword().equals(password) ) {
            throw new BusinessException(ApiCode.LOGIN_PASSWORD_EXCEPTION);
        }

        return admin;
    }
}