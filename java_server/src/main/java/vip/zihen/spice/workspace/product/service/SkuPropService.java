package vip.zihen.spice.workspace.product.service;

import vip.zihen.spice.workspace.product.entity.SkuProp;
import vip.zihen.spice.workspace.product.vo.SkuPropVo;

import java.util.List;

/**
 * (SkuProp)表服务接口
 *
 * @author wangjie
 * @since 2021-01-08 11:02:39
 */
public interface SkuPropService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkuProp queryById(Integer id);

    /**
     * 新增数据
     *
     * @param skuProp 实例对象
     * @return 实例对象
     */
    SkuProp insert(SkuProp skuProp);

    /**
     * 修改数据
     *
     * @param skuProp 实例对象
     * @return 实例对象
     */
    SkuProp update(SkuProp skuProp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 批量增加 ProductSkuProp
     * @param productId
     * @param skuPropVos
     * @return
     */
    boolean insertProductSkuPropBatch(int productId, List<SkuPropVo> skuPropVos);

    /**
     * 查询树状结构
     * @return 对象列表
     */
    List<SkuPropVo> queryTree();

    /**
     * 查询某个商品下关联的 skuProp树状结构
     * @return 对象列表
     */
    List<SkuPropVo> queryTreeBuProductId(int productId);

}