package com.dunshan.mall.member.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenghf
 * @date 2024-05-16
 * @desc 数据库配置文件
 */

@Slf4j
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    public DynamicDataSource dataSource(DataSource masterDatasource, DataSource shadowDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceNames.MASTER.getName(), masterDatasource);
        targetDataSources.put(DataSourceNames.SHADOW.getName(), shadowDataSource);
        log.info("DataSources:" + targetDataSources);
        return new DynamicDataSource(masterDatasource, targetDataSources);
    }

    /**
     * master数据源
     */
    @Primary
    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    /**
     * shadow数据源
     */
    @Bean(name = "shadowDataSource")
    @Qualifier("shadowDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shadow")
    public DataSource shadowDataSource() {
        return new DruidDataSource();
    }
}
