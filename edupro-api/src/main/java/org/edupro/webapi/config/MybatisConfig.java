package org.edupro.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.edupro.webapi.mapper")
@EnableAutoConfiguration
public class MybatisConfig {
}
