package com.yang.application.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
@Slf4j
@Order(3)
public class DruidConfiguration {


    static {
        log.info("加载DruidConfiguration...........");
    }

    @Autowired
    private DataSourceConfig dataSourceConfig;

    //@Primary 注解作用是当程序选择dataSource时选择被注解的这个
    @Bean
    @Primary
    public DataSource dataSource(){
        log.info("初始化dataSource...........");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDbType(dataSourceConfig.type); //设置数据库类型
        datasource.setUrl(dataSourceConfig.url); //数据库url
        datasource.setUsername(dataSourceConfig.username);
        datasource.setPassword(dataSourceConfig.password);
        datasource.setDriverClassName(dataSourceConfig.driverClassName);
        datasource.setInitialSize(dataSourceConfig.initialSize);
        datasource.setMinIdle(dataSourceConfig.minIdle);
        datasource.setMaxActive(dataSourceConfig.maxActive);
        datasource.setMaxWait(dataSourceConfig.maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceConfig.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(dataSourceConfig.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(dataSourceConfig.validationQuery);
        datasource.setTestWhileIdle(dataSourceConfig.testWhileIdle);
        datasource.setTestOnBorrow(dataSourceConfig.testOnBorrow);
        datasource.setTestOnReturn(dataSourceConfig.testOnReturn);
        datasource.setPoolPreparedStatements(dataSourceConfig.poolPreparedStatements);
        datasource.setConnectionInitSqls(Arrays.asList(dataSourceConfig.connectionInitSqls));
        try {
            datasource.setFilters(dataSourceConfig.filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", dataSourceConfig.username);
        reg.addInitParameter("loginPassword", dataSourceConfig.password);
        reg.addInitParameter("logSlowSql", dataSourceConfig.logSlowSql);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    /**
     * 数据库链接池信息
     */
    @Data @Configuration
    @PropertySource("classpath:druid.properties")
    @ConfigurationProperties("spring.datasource")
    static class DataSourceConfig {
        public  static final String type="mysql";
        /** 数据库信息 */
        public String url, username, password, driverClassName;

        /** 连接池信息 */
        public int initialSize, minIdle, maxActive, maxWait;

        public int timeBetweenEvictionRunsMillis, minEvictableIdleTimeMillis;

        public String validationQuery;

        public boolean testWhileIdle, testOnBorrow, testOnReturn, poolPreparedStatements;

        public String filters;

        public String logSlowSql;

        public String connectionInitSqls;
    }
}
