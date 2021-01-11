package vip.zihen.spice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 *
 * @author wangjie
 * @since 2021-01-05 10:20:54
 */
@Configuration
public class MybatisPlusConfig {

    /**
     *   mybatis-plus分页插件
     */
    @SuppressWarnings("deprecation")
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();

//        page.setDialectType("mysql");

        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        page.setCountSqlParser(new JsqlParserCountOptimize(true));
        return page;
    }

}