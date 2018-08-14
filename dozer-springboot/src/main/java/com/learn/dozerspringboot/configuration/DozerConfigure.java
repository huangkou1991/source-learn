package com.learn.dozerspringboot.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author :lwy
 * @date 2018/6/1 16:49
 */
@Configuration
public class DozerConfigure {


    @Bean
    public DozerBeanMapper instance(){
        DozerBeanMapper mapper=new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer_mapping.xml"));
        return mapper;
    }
}
