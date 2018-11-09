package com.yang.application.config;

import com.yang.application.runner.RedisRunner;
import com.yang.application.runner.StartupRunner;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Configuration
public class FilterConfiguration {

    /**
     * 现在假设我们的应用程序运行在一台负载均衡代理服务器后方，因此需要将代理服务器发来的请求包含的IP地址转换成真正的用户IP。
     * Tomcat 8 提供了对应的过滤器：RemoteIpFilter。
     * 通过将RemoteFilter这个过滤器加入过滤器调用链即可使用它。
     */
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UserFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("UserFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public StartupRunner schedulerRunner() {
        return new StartupRunner();
    }


    @Bean
    public RedisRunner schedulerRedisRunner() {
        return new RedisRunner();
    }

    /**
     * 定制自己的过滤去
     */
    public class UserFilter implements Filter {
        @Override
        public void destroy() {}

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            System.out.println("this is UserFilter,url :" + request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {}
    }

}
