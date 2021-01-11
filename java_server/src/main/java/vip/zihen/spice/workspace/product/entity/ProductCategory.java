package vip.zihen.spice.workspace.product.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (ProfuctCategory)实体类
 *
 * @author wangjie
 * @since 2021-01-08 10:53:04
 */
@Data
public class ProductCategory extends BaseEntity {
    private static final long serialVersionUID = -92285546747171357L;
    
    private Integer productId;
    
    private Integer categoryId;

}