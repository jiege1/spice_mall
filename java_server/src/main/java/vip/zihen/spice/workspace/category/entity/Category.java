package vip.zihen.spice.workspace.category.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import vip.zihen.spice.common.entity.BaseEntity;

/**
 * (Category)实体类
 *
 * @author wangjie
 * @since 2021-01-09 14:26:51
 */
@Data
@TableName(value = "category")
public class Category extends BaseEntity {

    /**
    * 商品分类标题
    */
    private String title;

    /**
    * 夫节点ID
    */
    private Integer parentId;

}