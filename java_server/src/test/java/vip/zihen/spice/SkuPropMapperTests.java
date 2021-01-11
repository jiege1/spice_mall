package vip.zihen.spice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vip.zihen.spice.workspace.product.entity.SkuProp;
import vip.zihen.spice.workspace.product.mapper.SkuPropMapper;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SkuPropMapperTests {

    @Resource
    private SkuPropMapper skuPropMapper;

    @Test
    void getList() {
        List<SkuProp> skuProps = skuPropMapper.selectList(new QueryWrapper<>(new SkuProp()));
    }

}
