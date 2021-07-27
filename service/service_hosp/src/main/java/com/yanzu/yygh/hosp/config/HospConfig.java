package com.yanzu.yygh.hosp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
@Configuration
@MapperScan("com.yanzu.yygh.hosp.dao")
public class HospConfig {
    /**
     * 分页插件
     * @return
     */
//    过时了
//    @Bean
//    public PaginationInterceptor  paginationInnerInterceptor(){
//        return new PaginationInterceptor ();
//    }
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;
    }

}
