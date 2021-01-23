package vip.zihen.spice.workspace.order.mapper;

import org.apache.ibatis.annotations.Insert;
import sun.jvm.hotspot.debugger.Page;
import vip.zihen.spice.workspace.order.entity.OrderItem;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * (OrderItem)表 Mapper
 *
 * @author wangjie
 * @since 2021-01-16 16:52:02
 */
@Repository
public interface OrderItemMapper extends BaseMapper<OrderItem> {

//    @Insert({
//            "<script>",
//            "INSERT INTO order_item(column1, column2, column3) VALUES ",
//            "<foreach collection='orderItems' item='item' index='index' separator=','>",
//            "(#{item.实体属性1}, #{item.实体属性2}, #{item.实体属性3})",
//            "</foreach>",
//            "</script>"
//    })
//    int insertBatch(List<OrderItem> orderItems);

}