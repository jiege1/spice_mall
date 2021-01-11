package vip.zihen.spice.workspace.category.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.zihen.spice.workspace.category.entity.Category;
import vip.zihen.spice.workspace.category.vo.CategoryVo;
import vip.zihen.spice.workspace.product.entity.Product;

import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author wangjie
 * @since 2021-01-09 14:22:22
 */
public interface CategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Integer id);

    /**
     * 查询商品分类数组状结构
     *
     * @return 对象列表
     */
    List<CategoryVo> queryTree();

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}