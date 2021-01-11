package vip.zihen.spice.workspace.category.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.zihen.spice.workspace.category.entity.ProductCategory;
import vip.zihen.spice.workspace.product.entity.Product;

import java.util.List;

/**
 * (ProductCategory)表服务接口
 *
 * @author wangjie
 * @since 2021-01-09 14:39:25
 */
public interface ProductCategoryService {

    /**
     * 获取分类下的商品列表
     * @param categoryId
     * @return
     */
    List<Product> getCategoryProducts(Integer categoryId);

    /**
     * 获取分类下的商品分页列表
     * @param page
     * @param categoryId
     * @return
     */
    Page<Product> getCategoryProductPage(Page page, Integer categoryId);

    /**
     * 添加商品和分类的关联关系
     * @param categoryId
     * @param productId
     * @return
     */
    ProductCategory insertCategoryProduct(Integer categoryId, Integer productId);

    /**
     * 向商品分类中批量添加商品
     * @param categoryId
     * @param productIds
     * @return
     */
    boolean insertCategoryProductBatch(Integer categoryId, List<Integer> productIds);

    /**
     * 删除商品和分类的关联关系
     * @param categoryId
     * @param productId
     * @return
     */
    boolean deleteCategoryProduct(Integer categoryId, Integer productId);

}