package com.gradle.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author :lwy
 * @date 2018/6/7 11:41
 */
@Configuration
@ImportResource(locations = {"application-grade.xml"})
@ComponentScan(basePackages = {"com.wade.gradle.learn"})
public class AbstractTest {
}
