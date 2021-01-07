package vip.zihen.spice.workspace.product.entity;

import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SkuProp)实体类
 *
 * @author wangjie
 * @since 2021-01-06 19:37:54
 */
@Data
public class SkuProp extends BaseEntity {
    private static final long serialVersionUID = 834686529278177758L;
    
    private Integer id;
    
    private String title;
    
    private Integer parentId;

    private SkuProp[] children;

}