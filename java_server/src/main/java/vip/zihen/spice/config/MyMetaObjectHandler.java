package vip.zihen.spice.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Supplier;

/**
 * mybatis自动填充字段（createTime，updateTime）
 * @author wangjie
 * @since 2021-01-05 10:20:54
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static String CREATE_TIME = "createTime";

    private static String UPDATE_TIME = "updateTime";

    private static String DELETED = "deleted";

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName(CREATE_TIME, new Date(), metaObject);
        this.setFieldValByName(UPDATE_TIME, new Date(), metaObject);
        this.setFieldValByName(DELETED, false, metaObject);

//        fillValue(metaObject, CREATE_TIME, () -> getDateValue(metaObject.getSetterType(CREATE_TIME)));
//        fillValue(metaObject, UPDATE_TIME, () -> getDateValue(metaObject.getSetterType(CREATE_TIME)));
//        fillValue(metaObject, DELETED, () -> false);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        log.info("start update fill ....");
//        this.setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    }

//    private void fillValue(MetaObject metaObject, String fieldName, Supplier<Object> valueSupplier) {
//        if (!metaObject.hasGetter(fieldName)) {
//            return;
//        }
//        Object sidObj = metaObject.getValue(fieldName);
//        if (sidObj == null && metaObject.hasSetter(fieldName) && valueSupplier != null) {
//            setFieldValByName(fieldName, valueSupplier.get(), metaObject);
//        }
//    }
//
//    private Object getDateValue(Class<?> setterType) {
//        if (Date.class.equals(setterType)) {
//            return new Date();
//        } else if (LocalDateTime.class.equals(setterType)) {
//            return LocalDateTime.now();
//        }
//        return null;
//    }
}
