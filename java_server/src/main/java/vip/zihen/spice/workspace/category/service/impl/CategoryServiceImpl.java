package vip.zihen.spice.workspace.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import vip.zihen.spice.common.utils.CommonUtils;
import vip.zihen.spice.workspace.category.entity.Category;
import vip.zihen.spice.workspace.category.mapper.CategoryMapper;
import vip.zihen.spice.workspace.category.service.CategoryService;
import vip.zihen.spice.workspace.category.vo.CategoryVo;
import vip.zihen.spice.workspace.product.entity.Product;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (Category)表服务实现类
 *
 * @author wangjie
 * @since 2021-01-09 14:22:23
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Integer id) {
        return categoryMapper.selectById(id);
    }

    /**
     * 获取商品分类树
     * @return
     */
    @Override
    public List<CategoryVo> queryTree() {
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<>(new Category()));
        return categoriesToCategoryVoTree(categories);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        int id = categoryMapper.insert(category);
        category.setId(id);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        categoryMapper.update(category, new UpdateWrapper<>(category));
        return queryById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return categoryMapper.deleteById(id) > 0;
    }

    /**
     * 通过 Category List 生成 CategoryVo Tree
     * @param categories
     * @return
     */
    private List<CategoryVo> categoriesToCategoryVoTree(List<Category> categories) {

        Map<Integer, List<Category>> categoryMap = new HashMap<>();

        categories.forEach(category -> {
            int parentId = category.getParentId();
            List<Category> parentChildren = categoryMap.get(parentId);

            if (Objects.isNull(parentChildren)) {
                parentChildren = new ArrayList<>();
                categoryMap.put(parentId, parentChildren);
            }

            parentChildren.add(category);
        });

        List<Category> rootCategories = categoryMap.get(0);

        if (Objects.isNull(rootCategories)) {
            return new ArrayList<>();
        }

        return rootCategories.stream()
                .map(category -> createCategoryVoByCategories(category, categoryMap))
                .collect(Collectors.toList());
    }

    /**
     * 通过 Category list 生成 CategoryVo
     * @return
     */
    private CategoryVo createCategoryVoByCategories(Category category,
                                                 Map<Integer, List<Category>> categoryMap) {
        CategoryVo categoryVo = CommonUtils.convertData(category, CategoryVo.class);

        // 遍历子节点
        List<Category> children = categoryMap.get(category.getId());
        if (!Objects.isNull(children)) {
            List<CategoryVo> childVos = children.stream()
                    .map(child -> createCategoryVoByCategories(child, categoryMap))
                    .collect(Collectors.toList());
            categoryVo.setChildren(childVos);
        }

        return categoryVo;
    }

}