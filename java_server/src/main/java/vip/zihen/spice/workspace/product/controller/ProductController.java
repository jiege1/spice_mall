package vip.zihen.spice.workspace.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import vip.zihen.spice.common.Utils.RequestUtils;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.workspace.product.entity.Product;
import vip.zihen.spice.workspace.product.entity.Sku;
import vip.zihen.spice.workspace.product.entity.SkuProp;
import vip.zihen.spice.workspace.product.service.ProductService;
import org.springframework.web.bind.annotation.*;
import vip.zihen.spice.workspace.product.service.SkuService;
import vip.zihen.spice.workspace.product.vo.ProductVo;
import vip.zihen.spice.workspace.user.entity.UserRole;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (Product)表控制层
 *
 * @author wangjie
 * @since 2021-01-06 11:14:01
 */
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    @Resource
    private SkuService skuService;

    /**
     * 获取商品列表
     * @param page
     * @param size
     * @param keywords
     * @return
     */
    @LoginRequired(roles = {UserRole.ADMIN})
    @GetMapping
    public ApiResult selectPage(
            HttpServletRequest req,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "keywords", required = false) String keywords) {

//        // get userId by request
//        int userId = RequestUtils.getUserId(req);

        Product product = new Product();
        if (StringUtils.isNotBlank(keywords)) {
            product.setTitle(keywords);
        }

        Page productPage = productService.queryPage(page, size, product);

        return ApiResult.ok(productPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @LoginRequired(roles = {UserRole.ADMIN})
    @GetMapping("/{id}")
    public ApiResult<Product> selectOne(@PathVariable Integer id) {
        return ApiResult.ok(productService.queryById(id));
    }

    /**
     * 新增商品
     * @param product
     * @return
     */
    @LoginRequired(roles = {UserRole.ADMIN})
    @PostMapping
    public ApiResult<Product> insert(@RequestBody ProductVo product) {
        List<Sku> skus = product.getSkus();
        List<SkuProp> skuProps = product.getSkuProps();

        skuService.insertBatch(skus);

        // todo:添加 product_sku_prop

        return ApiResult.ok(productService.insert(product));
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @LoginRequired(roles = {UserRole.ADMIN})
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable int id) {
        return ApiResult.ok(productService.deleteById(id));
    }

}