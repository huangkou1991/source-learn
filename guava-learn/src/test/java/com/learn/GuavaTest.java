package com.learn;

import com.learn.guava.domain.User;
import org.junit.Test;

/**
 * @author :lwy
 * @date 2018/6/11 18:18
 */
public class GuavaTest {


    @Test
    public void testMoreObjects() {
        User user = new User();
        user.setId(2);
        user.setName("wade");
        System.out.println(user.toString());
    }
}
