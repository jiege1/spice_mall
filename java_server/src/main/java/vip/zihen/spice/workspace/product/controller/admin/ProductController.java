package vip.zihen.spice.workspace.product.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.common.utils.CommonUtils;
import vip.zihen.spice.config.auth.LoginRequired;
import vip.zihen.spice.workspace.product.entity.Product;
import vip.zihen.spice.workspace.product.entity.Sku;
import vip.zihen.spice.workspace.product.entity.SkuProp;
import vip.zihen.spice.workspace.product.service.ProductService;
import vip.zihen.spice.workspace.product.service.SkuPropService;
import vip.zihen.spice.workspace.product.service.SkuService;
import vip.zihen.spice.workspace.product.vo.ProductVo;
import vip.zihen.spice.workspace.product.vo.SkuPropVo;
import vip.zihen.spice.workspace.user.entity.UserRole;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * (Product)表控制层
 *
 * @author wangjie
 * @since 2021-01-06 11:14:01
 */
@Api(tags = "admin端 商品模块")
@RestController
@RequestMapping("b/product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;
    /**
     * sku
     */
    @Resource
    private SkuService skuService;
    /**
     * skuProp
     */
    @Resource
    private SkuPropService skuPropService;

    /**
     * 获取商品列表
     * @param page
     * @param size
     * @param keywords
     * @return
     */
    @ApiOperation("获取商品分页列表，")
    @LoginRequired(roles = {UserRole.ADMIN})
    @GetMapping
    public ApiResult selectPage(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "keywords", required = false) String keywords) {

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
    @ApiOperation("通过商品ID获取商品详情")
    @LoginRequired(roles = {UserRole.ADMIN})
    @GetMapping("/{id}")
    public ApiResult selectOne(@PathVariable Integer id) {

        Product product = productService.queryById(id);

        if (Objects.isNull(product)) {
            return ApiResult.fail("商品不存在！");
        }

        ProductVo productVo = CommonUtils.convertData(product, ProductVo.class);

        // 查询sku
        Sku skuQuery = new Sku();
        skuQuery.setProductId(id);
        List<Sku> skus = skuService.queryList(skuQuery);
        productVo.setSkus(skus);

        // 查询关联skuProps
        List<SkuPropVo> skuPropVos = skuPropService.queryTreeBuProductId(id);
        productVo.setSkuProps(skuPropVos);


        return ApiResult.ok(productVo);
    }

    /**
     * 新增商品
     * @param product
     * @return
     */
    @ApiOperation("新增商品")
    @LoginRequired(roles = {UserRole.ADMIN})
    @PostMapping
    public ApiResult<Product> insert(@RequestBody ProductVo product) {

        List<Sku> skus = product.getSkus();
        List<SkuPropVo> skuProps = product.getSkuProps();

        Product product1 = productService.insert(product);

        // 如果存在商品SKU，先添加 sku，productSkuProp，然后再添加商品
        if (!skus.isEmpty() && !skuProps.isEmpty()) {
            skus.forEach(sku -> sku.setProductId(product1.getId()));
            skuService.insertBatch(skus);
            skuPropService.insertProductSkuPropBatch(product1.getId(), skuProps);
        }

        return ApiResult.ok(product1);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @ApiOperation("删除商品")
    @LoginRequired(roles = {UserRole.ADMIN})
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable int id) {
        return ApiResult.ok(productService.deleteById(id));
    }

    /**
     * 获取skuProp树
     * @return
     */
    @ApiOperation("获取商品SKuProp树状结构")
    @LoginRequired(roles = {UserRole.ADMIN})
    @GetMapping("/sku-prop")
    public ApiResult<List<SkuPropVo>> getSkuPropTree() {
        return ApiResult.ok(skuPropService.queryTree());
    }

    /**
     * 新增skuProp
     * @param skuProp
     * @return
     */
    @ApiOperation("新增商品SkuProp")
    @LoginRequired(roles = {UserRole.ADMIN})
    @PostMapping("/sku-prop")
    public ApiResult createSkuProp(@RequestBody SkuProp skuProp) {
        int parentId = skuProp.getParentId();
        if (parentId > 0) {
            SkuProp parent = skuPropService.queryById(parentId);
            if (Objects.isNull(parent)) {
                return ApiResult.fail("未查询到 父节点ID为 " + parentId + " 的SkuProp!");
            }
        }
        return ApiResult.ok(skuPropService.insert(skuProp));
    }

    @ApiOperation("删除SkuProp")
    @LoginRequired(roles = {UserRole.ADMIN})
    @DeleteMapping("/sku-prop/{id}")
    public ApiResult deleteSkuProp(@PathVariable Integer id) {
        boolean result = skuPropService.deleteById(id);
        return ApiResult.ok(result);
    }

}