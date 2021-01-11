package vip.zihen.spice.workspace.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import vip.zihen.spice.workspace.product.entity.Sku;
import vip.zihen.spice.workspace.product.mapper.SkuMapper;
import vip.zihen.spice.workspace.product.service.SkuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Sku)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-06 19:48:03
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuMapper skuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Sku queryById(Integer id) {
        return skuMapper.selectById(id);
    }

    /**
     * 查询列表
     * @param query 查询条件
     * @return
     */
    @Override
    public List<Sku> queryList(Sku query) {
        return skuMapper.selectList(new QueryWrapper<>(query));
    }

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public Sku insert(Sku sku) {
        skuMapper.insert(sku);
        return sku;
    }

    /**
     * 批量插入
     * @param skus
     * @return
     */
    @Override
    public boolean insertBatch(List<Sku> skus) {
        skus.forEach(sku -> insert(sku));
        return true;
    }

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public Sku update(Sku sku) {
        skuMapper.update(sku, new UpdateWrapper<Sku>(sku));
        return queryById(sku.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return skuMapper.deleteById(id) > 0;
    }
}