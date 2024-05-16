package com.dunshan.mall.member.config;

import brave.baggage.BaggageField;
import com.dunshan.mall.common.config.AppContext;
import com.dunshan.mall.common.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zhenghf
 * @date 2024-05-16
 * @desc Servlet全局拦截filter, 将压测标记写入AppContext
 */

@Slf4j
@Component
public class ContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        BaggageField dunshan = BaggageField.getByName(AuthConstant.TRAFFIC_SIGNS);
        String value = dunshan.getValue();
        AppContext appContext = new AppContext();

        // 获取流量标记并存入数据上下文
        log.info("ContextFilter, BaggageField value: " + value);
        if (StringUtils.isNotBlank(value)) {
            appContext.setFlag(value);
            AppContext.setContext(appContext);
        } else {
            appContext.setFlag("");
            AppContext.setContext(appContext);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        AppContext.removeContext();
        Filter.super.destroy();
    }
}
