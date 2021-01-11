package vip.zihen.spice.workspace.category.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.common.utils.CommonUtils;
import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.workspace.category.entity.Category;
import vip.zihen.spice.workspace.category.entity.ProductCategory;
import vip.zihen.spice.workspace.category.service.CategoryService;
import vip.zihen.spice.workspace.category.service.ProductCategoryService;
import vip.zihen.spice.workspace.category.vo.BatchProductIds;
import vip.zihen.spice.workspace.category.vo.CategoryVo;
import vip.zihen.spice.workspace.product.entity.Product;
import vip.zihen.spice.workspace.user.entity.UserRole;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Category)表控制层
 *
 * @author wangjie
 * @since 2021-01-09 14:22:24
 */
@Api(tags = "admin 商品分类")
@RestController
@RequestMapping("b/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;
    /**
     * 商品-分类 关联关系 服务对象
     */
    @Resource
    private ProductCategoryService productCategoryService;

    /**
     * 获取商品分类树
     * @return
     */
    @ApiOperation("获取商品分类树")
    @LoginRequired(roles = {UserRole.ADMIN})
    @GetMapping
    public ApiResult<List<CategoryVo>> queryTree() {
        return ApiResult.ok(categoryService.queryTree());
    }

    /**
     * 新增分类
     * @param category
     * @return
     */
    @ApiOperation("新增分类")
    @LoginRequired(roles = {UserRole.ADMIN})
    @PostMapping
    public ApiResult<Category> create(@RequestBody Category category) {
        return ApiResult.ok(categoryService.insert(category));
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @ApiOperation("删除分类")
    @LoginRequired(roles = {UserRole.ADMIN})
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Integer id) {
        return ApiResult.ok(categoryService.deleteById(id));
    }

    /**
     * 修改分类
     * @param id
     * @param category
     * @return
     */
    @ApiOperation("修改分类")
    @LoginRequired(roles = {UserRole.ADMIN})
    @PutMapping("/{id}")
    public ApiResult<Category> update(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        return ApiResult.ok(categoryService.update(category));
    }

    /**
     * 向分类中添加单个商品
     * @param id
     * @param productId
     * @return
     */
    @ApiOperation("向分类中添加单个商品")
    @LoginRequired(roles = {UserRole.ADMIN})
    @PostMapping("/{id}/product/{productId}")
    public ApiResult<ProductCategory> insertProductToCategory(@PathVariable Integer id, @PathVariable Integer productId) {
        return ApiResult.ok(productCategoryService.insertCategoryProduct(id, productId));
    }

    /**
     * 向分类中批量添加商品
     * @param id
     * @return
     */
    @ApiOperation("向分类中批量添加商品")
    @LoginRequired(roles = {UserRole.ADMIN})
    @PostMapping("/{id}/product/batch")
    public ApiResult<Boolean> insertProductToCategory(@PathVariable Integer id, @RequestBody BatchProductIds productIds) {

        List<Integer> ids = Arrays.stream(productIds.getProductIds().split(","))
                .map(productId -> Integer.valueOf(productId))
                .collect(Collectors.toList());

        return ApiResult.ok(productCategoryService.insertCategoryProductBatch(id, ids));
    }

    /**
     * 删除分类中的单个商品
     * @param id
     * @param productId
     * @return
     */
    @ApiOperation("删除分类中的单个商品")
    @LoginRequired(roles = {UserRole.ADMIN})
    @DeleteMapping("/{id}/product/{productId}")
    public ApiResult<Boolean> deleteProductToCategory(@PathVariable Integer id, @PathVariable Integer productId) {
        return ApiResult.ok(productCategoryService.deleteCategoryProduct(id ,productId));
    }

    /**
     * 获取分类下的商品列表
     * @param id
     * @param page
     * @param size
     * @param keywords
     * @return
     */
    @ApiOperation("获取分类下的商品列表")
    @LoginRequired(roles = {UserRole.ADMIN})
    @GetMapping("/{id}/product")
    public ApiResult<Page<Product>> getCategoryProducts(
            @PathVariable Integer id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "keywords", required = false) String keywords) {
        Page<Product> result = productCategoryService.getCategoryProductPage(new Page(page, size), id);
        return ApiResult.ok(result);
    }

}