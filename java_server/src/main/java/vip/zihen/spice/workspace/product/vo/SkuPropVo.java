package vip.zihen.spice.workspace.product.vo;

import lombok.Data;
import vip.zihen.spice.workspace.product.entity.SkuProp;

import java.util.List;
import java.util.Objects;

@Data
public class SkuPropVo extends SkuProp {

    private List<SkuPropVo> children;

    /**
     * 通过skuProp，设置属性
     * @param skuProp
     */
    public void setDataFromSkuProp(SkuProp skuProp) {
        if (Objects.isNull(skuProp)) {
            return;
        }
        this.setId(skuProp.getId());
        this.setParentId(skuProp.getParentId());
        this.setTitle(skuProp.getTitle());
        this.setCreateTime(skuProp.getCreateTime());
        this.setUpdateTime(skuProp.getUpdateTime());
        this.setDeleted(skuProp.getDeleted());
    }

}
