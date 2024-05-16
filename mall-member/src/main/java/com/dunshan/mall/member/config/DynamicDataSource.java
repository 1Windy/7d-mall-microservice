package com.dunshan.mall.member.config;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author zhenghf
 * @date 2024-05-16
 * @desc 动态数据源切换
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final TransmittableThreadLocal<String> contextHolder = new TransmittableThreadLocal<>();

    /**
     * 配置DataSource, defaultTargetDataSource为主数据库
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
