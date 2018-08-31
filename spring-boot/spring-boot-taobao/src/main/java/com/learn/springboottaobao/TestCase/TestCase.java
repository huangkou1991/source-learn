package com.learn.springboottaobao.TestCase;

import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressTask;
import org.springframework.web.client.RestTemplate;

/**
 * @Author :lwy
 * @Date : 2018/8/31 10:09
 * @Description :
 * jar包在百度云盘中存储。
 */
public class TestCase {

    public static void main(String[] args) {

        RestTemplate template = new RestTemplate();

        StressTestUtils.testAndPrint(1000, 1000, new StressTask() {
            @Override
            public Object doTask() throws Exception {
                //template.getForObject("http://localhost:8081/api/articles", String.class);

                template.getForObject("http://localhost:8080/withHystrix", String.class);
                return null;
            }
        });
        System.exit(0);
    }
}
