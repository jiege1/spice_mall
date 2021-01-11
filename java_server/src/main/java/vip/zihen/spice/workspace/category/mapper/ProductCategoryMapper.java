package vip.zihen.spice.workspace.category.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vip.zihen.spice.workspace.category.entity.ProductCategory;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import vip.zihen.spice.workspace.product.entity.Product;

import java.util.List;

/**
 * (ProductCategory)表 Mapper
 *
 * @author wangjie
 * @since 2021-01-09 14:39:25
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    @Select("SELECT p.id, p.title, price, p.image, p.status, p.virtual_sales, p.stock " +
            "FROM product p, product_category c " +
            "WHERE c.product_id = p.id " +
            "AND c.category_id = #{categoryId}")
    List<Product> selectProductFromCategory(@Param(value = "categoryId") Integer categoryId);

    @Select("SELECT p.id, p.title, p.price, p.image, p.status, p.virtual_sales, p.stock " +
            "FROM product p, product_category c " +
            "WHERE c.product_id = p.id " +
            "AND c.category_id = #{categoryId}")
    Page<Product> selectPageProductFromCategory(Page page, Integer categoryId);

}