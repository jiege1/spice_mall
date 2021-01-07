package vip.zihen.spice.workspace.product.vo;

import lombok.Data;
import vip.zihen.spice.workspace.product.entity.Product;
import vip.zihen.spice.workspace.product.entity.Sku;
import vip.zihen.spice.workspace.product.entity.SkuProp;

import java.util.List;

@Data
public class ProductVo extends Product {

    private List<Sku> skus;

    private List<SkuProp> skuProps;

}
