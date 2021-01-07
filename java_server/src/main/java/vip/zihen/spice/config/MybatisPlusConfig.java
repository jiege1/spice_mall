package vip.zihen.spice.config;

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
        page.setDialectType("mysql");
        return page;
    }

}