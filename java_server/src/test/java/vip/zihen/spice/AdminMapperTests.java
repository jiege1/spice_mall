package vip.zihen.spice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vip.zihen.spice.workspace.admin.entity.Admin;
import vip.zihen.spice.workspace.admin.mapper.AdminMapper;

import javax.annotation.Resource;

@SpringBootTest
public class AdminMapperTests {

    @Resource
    private AdminMapper adminMapper;

    @Test
    public void delete() {
        adminMapper.deleteById(5);
    }

    @Test
    public void insert() {
        Admin admin = new Admin();
        admin.setUsername("111111");
        admin.setPassword("111111");
        admin.setName("wangjie");
        admin.setDeleted(false);
        adminMapper.insert(admin);
    }

    @Test
    public void selectList() {
        adminMapper.selectList(new QueryWrapper<>(new Admin()));
    }
}
