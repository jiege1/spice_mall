package vip.zihen.spice.workspace.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import vip.zihen.spice.workspace.product.entity.SkuProp;

import java.util.List;

/**
 * (SkuProp)表 Mapper
 *
 * @author wangjie
 * @since 2021-01-08 11:05:13
 */
@Repository
public interface SkuPropMapper extends BaseMapper<SkuProp> {

    @Select("SElECT * FROM sku_prop WHERE deleted = 0 AND id in (${ids})")
    List<SkuProp> selectListByIds(@Param("ids") String ids);

}