package vip.zihen.spice.workspace.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import vip.zihen.spice.workspace.product.entity.Product;
import vip.zihen.spice.workspace.product.mapper.ProductMapper;
import vip.zihen.spice.workspace.product.service.ProductService;

import javax.annotation.Resource;

/**
 * (Product)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-06 11:14:01
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Integer id) {
        return productMapper.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Page<Product> queryPage(int offset, int limit, Product query) {
        return productMapper.selectPage(new Page<>(offset, limit), new QueryWrapper<>(query));
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        int id = productMapper.insert(product);
        product.setId(id);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        productMapper.update(product, new UpdateWrapper<Product>(product));
        return queryById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return productMapper.deleteById(id) > 0;
    }
}