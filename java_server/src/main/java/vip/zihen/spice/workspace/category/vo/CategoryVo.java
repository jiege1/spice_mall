package vip.zihen.spice.workspace.category.vo;

import lombok.Data;
import vip.zihen.spice.workspace.category.entity.Category;

import java.util.List;

@Data
//@Tab
public class CategoryVo extends Category {

    private List<CategoryVo> children;

}
