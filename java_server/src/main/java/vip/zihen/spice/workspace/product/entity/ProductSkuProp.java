package vip.zihen.spice.workspace.product.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (ProductSkuProp)实体类
 *
 * @author wangjie
 * @since 2021-01-08 10:57:04
 */
@Data
public class ProductSkuProp extends BaseEntity {
    private static final long serialVersionUID = -27248466109606241L;
    
    private Integer productId;
    
    private Integer propId;
    
    private String valueIds;

}