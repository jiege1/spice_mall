package vip.zihen.spice.workspace.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import vip.zihen.spice.workspace.category.entity.ProductCategory;
import vip.zihen.spice.workspace.category.mapper.ProductCategoryMapper;
import vip.zihen.spice.workspace.category.service.ProductCategoryService;
import vip.zihen.spice.workspace.product.entity.Product;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ProductCategory)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-09 14:39:25
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private ProductCategoryMapper mapper;

    @Override
    public List<Product> getCategoryProducts(Integer categoryId) {
        return mapper.selectProductsFromCategory(categoryId);
    }

    // todo
    @Override
    public Page<Product> getCategoryProductPage(Page page, Integer categoryId) {
        return mapper.selectProductsFromCategory(page, categoryId);
    }

    @Override
    public ProductCategory insertCategoryProduct(Integer categoryId, Integer productId) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductId(productId);
        productCategory.setCategoryId(categoryId);
        int id = mapper.insert(productCategory);
        productCategory.setId(id);
        return productCategory;
    }

    @Override
    public boolean insertCategoryProductBatch(Integer categoryId, List<Integer> productIds) {
        productIds.forEach(productId -> insertCategoryProduct(categoryId, productId));
        return true;
    }

    @Override
    public boolean deleteCategoryProduct(Integer categoryId, Integer productId) {
        ProductCategory deleteQuery = new ProductCategory();
        deleteQuery.setCategoryId(categoryId);
        deleteQuery.setProductId(productId);
        return mapper.delete(new QueryWrapper<>(deleteQuery)) > 0;
    }
}