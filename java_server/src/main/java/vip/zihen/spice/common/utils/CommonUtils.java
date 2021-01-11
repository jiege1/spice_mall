package vip.zihen.spice.common.utils;

import com.alibaba.fastjson.JSONObject;
import vip.zihen.spice.common.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {

    /**
     * 根据夫类实例 生成 相同数据的子类实例
     * @param parentData
     * @param clazz
     * @param <P>
     * @param <C>
     * @return
     */
    public static <P, C extends P> C convertData(P parentData, Class<C> clazz) {
        return (C) JSONObject.parseObject(
                JSONObject.toJSONString(parentData),
                clazz
        );
    }

    /**
     * array -> list
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T[] array) {
        List<T> list = new ArrayList<>(array.length);
        for (T s : array) {
            list.add(s);
        }
        return list;
    }

    /**
     * list -> tree
     * @param list
     * @param <T>
     * @return
     */
//    public static <T> List<T> listToTree(List<T> list) {
//
//    }

    /**
     * 遍历获取 Entity 中的 id 集合
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> List<Integer> getEntityIds(List<T> list) {
        return list.stream().map(T::getId).collect(Collectors.toList());
    }

}
