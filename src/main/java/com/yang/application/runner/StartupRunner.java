package com.yang.application.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * 对于那种只需要在应用程序启动时执行一次的任务，非常适合利用Command line runners来完成。
 *
 * Spring Boot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。
 *
 * 也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序
 */

@Order(1)
public class StartupRunner implements CommandLineRunner {
    protected final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("StartupRunner sss..........");
    }

}
