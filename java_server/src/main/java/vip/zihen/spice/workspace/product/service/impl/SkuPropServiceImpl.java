package vip.zihen.spice.workspace.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import vip.zihen.spice.common.utils.CommonUtils;
import vip.zihen.spice.workspace.product.entity.ProductSkuProp;
import vip.zihen.spice.workspace.product.entity.SkuProp;
import vip.zihen.spice.workspace.product.mapper.ProductSkuPropMapper;
import vip.zihen.spice.workspace.product.mapper.SkuPropMapper;
import vip.zihen.spice.workspace.product.service.SkuPropService;
import vip.zihen.spice.workspace.product.vo.SkuPropVo;

import javax.annotation.Resource;
import java.util.*;

/**
 * (SkuProp)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-08 11:02:39
 */
@Service("skuPropService")
public class SkuPropServiceImpl implements SkuPropService {
    @Resource
    private SkuPropMapper skuPropMapper;
    @Resource
    private ProductSkuPropMapper productSkuPropMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkuProp queryById(Integer id) {
        return skuPropMapper.selectById(id);
    }

    /**
     * 新增数据
     *
     * @param skuProp 实例对象
     * @return 实例对象
     */
    @Override
    public SkuProp insert(SkuProp skuProp) {
        int id = skuPropMapper.insert(skuProp);
        skuProp.setId(id);
        return skuProp;
    }

    /**
     * 修改数据
     *
     * @param skuProp 实例对象
     * @return 实例对象
     */
    @Override
    public SkuProp update(SkuProp skuProp) {
        skuPropMapper.update(skuProp, new UpdateWrapper<>(skuProp));
        return queryById(skuProp.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return skuPropMapper.deleteById(id) > 0;
    }

    /**
     * 批量添加 product 和 skuProps 关系
     * @param productId
     * @param skuPropVos
     * @return
     */
    @Override
    public boolean insertProductSkuPropBatch(int productId, List<SkuPropVo> skuPropVos) {

        skuPropVos.forEach(skuProp -> {
            List<SkuPropVo> skuVales = skuProp.getChildren();


            if (skuVales.size() <= 0) {
                throw new NullPointerException("给商品添加sku时，请给相关的 skuProp 选择 skuValue!");
            }

            List<Integer> valueIds = new ArrayList<Integer>();

            skuVales.forEach(skuVale -> valueIds.add(skuVale.getId()));

            ProductSkuProp productSkuProp = new ProductSkuProp();
            productSkuProp.setProductId(productId);
            productSkuProp.setValueIds(StringUtils.join(valueIds, ","));
            productSkuProp.setPropId(skuProp.getId());

            productSkuPropMapper.insert(productSkuProp);
        });

        return false;
    }

    /**
     * 获取 skuProps 树状结构
     * @return
     */
    @Override
    public List<SkuPropVo> queryTree() {
        List<SkuProp> skuProps = skuPropMapper.selectList(new QueryWrapper<>(new SkuProp()));
        return mapSkuPropListToTree(skuProps);
    }

    /**
     * 查询商品关联的 skuProp 树
     * @param productId
     * @return
     */
    @Override
    public List<SkuPropVo> queryTreeBuProductId(int productId) {

        // skuProps，skuValue所有ID集合
        List<String> skuPropIds = new ArrayList<>();

        ProductSkuProp query = new ProductSkuProp();
        query.setProductId(productId);
        List<ProductSkuProp> productSkuProps = productSkuPropMapper.selectList(new QueryWrapper<>(query));

        productSkuProps.forEach(productSkuProp -> {
            // 先创建skuProp
            skuPropIds.add(productSkuProp.getPropId().toString());
            // 在创建skuValue
            String valueIdsStr = productSkuProp.getValueIds();
            if (StringUtils.isNotBlank(valueIdsStr)) {
                skuPropIds.add(valueIdsStr);
            }
        });

        List<SkuProp> skuProps = skuPropMapper.selectListByIds(StringUtils.join(skuPropIds, ","));

        return mapSkuPropListToTree(skuProps);
    }

    /**
     * SkuProp list 转 SkuPropVo tree
     * @param skuProps
     * @return
     */
    private List<SkuPropVo> mapSkuPropListToTree(List<SkuProp> skuProps) {
        Map<Integer, List<SkuPropVo>> listMap = new HashMap<Integer, List<SkuPropVo>>();

        skuProps.forEach(skuProp -> {
            List<SkuPropVo> parentSkuPropList = listMap.get(skuProp.getParentId());

            SkuPropVo skuPropVo = CommonUtils.convertData(skuProp, SkuPropVo.class);

            if (parentSkuPropList == null) {
                List<SkuPropVo> skuPropList = new ArrayList<SkuPropVo>();
                skuPropList.add(skuPropVo);
                listMap.put(skuPropVo.getParentId(), skuPropList);
            } else {
                parentSkuPropList.add(skuPropVo);
            }
        });
        // skuProp 大类
        List<SkuPropVo> rootSkuProps = listMap.get(0);
        if (Objects.isNull(rootSkuProps)) {
            return new ArrayList<>();
        }

        rootSkuProps.forEach(rootSkuProp -> {
            List<SkuPropVo> children = listMap.get(rootSkuProp.getId());
            if (Objects.isNull(children)) {
                children = new ArrayList<>();
            }
            rootSkuProp.setChildren(children);
        });

        return rootSkuProps;
    }
}