package vip.zihen.spice.workspace.product.service;

import vip.zihen.spice.workspace.product.entity.Sku;
import vip.zihen.spice.workspace.product.entity.SkuProp;

import java.util.List;

/**
 * (Sku)表服务接口
 *
 * @author wangjie
 * @since 2021-01-06 19:48:03
 */
public interface SkuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Sku queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param query 查询条件
     * @return 对象列表
     */
    List<Sku> queryList(Sku query);

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    Sku insert(Sku sku);

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    Sku update(Sku sku);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 批量插入
     * @param skus
     * @return
     */
    boolean insertBatch(List<Sku> skus);

}