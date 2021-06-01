package com.yanzu.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
@Configuration
@MapperScan("com.yanzu.yygh.hosp.dao")
public class HospConfig {
}
