package com.wade.springbootwebakka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author :lwy
 * @Date : 2018/8/24 17:38
 * @Description :
 */
@Service
public class BusinessService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void perform(Object o) {
        logger.info("Perform: {}", o);
    }
}
