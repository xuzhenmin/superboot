package com.zhenmin.superboot.test;

import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
import com.zhenmin.superboot.common.constant.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan({ConfigConstants.REG_BEAN_SCAN})
//@ImportResource(locations = {"classpath:application-bean.xml"})
@EnableDubboConfiguration
public class SuperBootStartUpForTest {
    private static final Logger log = LoggerFactory.getLogger(SuperBootStartUpForTest.class);

    public static void main(String[] args) {
        log.info("Let's inspect the beans provided by Spring Boot:");
        ApplicationContext ctx = SpringApplication.run(SuperBootStartUpForTest.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info("inspect bean {}", beanName);
        }
        log.info("-----------------SuperBoot api start up success--------------------");
    }
}
