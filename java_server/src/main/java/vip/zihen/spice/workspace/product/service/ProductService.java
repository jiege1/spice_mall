package vip.zihen.spice.workspace.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.zihen.spice.workspace.product.entity.Product;

/**
 * (Product)表服务接口
 *
 * @author wangjie
 * @since 2021-01-06 11:14:01
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Page<Product> queryPage(int offset, int limit, Product query);

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}