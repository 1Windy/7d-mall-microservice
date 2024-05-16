package com.dunshan.mall.member.aop;

import com.dunshan.mall.common.config.AppContext;
import com.dunshan.mall.member.config.DataSourceNames;
import com.dunshan.mall.member.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhenghf
 * @date 2024-05-16
 * @desc
 */

@Slf4j
@Component
@Aspect
public class DataSourceSwitch {
    /**
     * 拦截入口下所有的 public方法
     */
    @Pointcut("execution(public * com.dunshan.mall.*.controller..*(..))")
    public void pointCutAround() {
    }

    /**
     * 根据数据上下AppContext文切换数据源
     */
    @Before(value = "pointCutAround()")
    public void around(JoinPoint point) {
        AppContext context = AppContext.getContext();
        String flag = context.getFlag();
        log.info("AppContext flag: " + flag);

        if (StringUtils.isNotBlank(flag) && flag.equals(DataSourceNames.SHADOW.getName())) {
            DynamicDataSource.setDataSource(DataSourceNames.SHADOW.getName());
        } else {
            DynamicDataSource.setDataSource(DataSourceNames.MASTER.getName());
        }
    }
}
