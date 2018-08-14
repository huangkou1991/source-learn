package com.learn;

import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.net.InetAddresses;
import com.learn.guava.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;


/**
 * @author :lwy
 * @date 2018/6/11 18:40
 */
public class FluentIterableTest {

    @Test
    public void fluentIterable() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", 45));
        users.add(new User(2L, "Michelle", 27));
        users.add(new User(3L, "Max", 16));
        users.add(new User(4L, "Sue", 10));
        users.add(new User(5L, "Bill", 65));
        Predicate<User> byAge = user -> user.getAge() > 19;
        List<String> results = users.stream().filter(byAge)
                .map(Functions.toStringFunction()).collect(Collectors.toList());

        List<String> results2 =
                FluentIterable.from(users).filter(byAge)
                        .transform(Functions.toStringFunction())
                        .toList();
        System.err.println(results2);
    }


    @Test
    public void test() {
        //可以为一个数组动态的扩容
        User[] usersArray = {new User(1L, "John", 45), new User(2L, "Max", 15)};

        FluentIterable<User> users = FluentIterable.of(usersArray).append(
                new User(3L, "Sue", 23),
                new User(4L, "Bill", 17)
        );
        System.err.println(users.size());


    }


    @Test
    public void test2() {
        //可以为一个数组动态的扩容
        User[] usersArray = {new User(1L, "John", 45), new User(2L, "Max", 15)};
        List<User> list = new ArrayList<>();
        list.add(new User(3L, "Wade", 37));
        FluentIterable<User> users = FluentIterable.of(usersArray).append(list);

        System.err.println(users.size());
    }

    @Test
    public void test3() {
        //可以为一个数组动态的扩容
        User[] usersArray = {new User(1L, "John", 45), new User(2L, "Max", 15)};
        FluentIterable<User> users = FluentIterable.of(usersArray);
        String result = users.join(Joiner.on("; "));
        System.err.println(result);
    }

    @Test
    //hash算法  固定位数的hashCode计算方法
    public void hashTest() {
        int resources = 1234564;
        HashCode hashCode = Hashing.crc32c().hashInt(resources);
        System.out.println(hashCode);
    }

    @Test
    public void intAddressTest() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("127.0.0.5");
        InetAddress decrementedAddress = InetAddresses.decrement(address);

        Assert.assertThat(decrementedAddress.toString(), equalTo("/127.0.0.4"));
    }

    @Test
    //线程
    public void testThread(){
        ConcurrentHashMap<String, Boolean> threadExecutions = new ConcurrentHashMap<>();
        Runnable logThreadRun = () -> threadExecutions.put(Thread.currentThread().getName(), true);

        Thread t = new Thread(logThreadRun);
        t.start();

        Assert.assertTrue(threadExecutions.get("main"));
        System.out.println(threadExecutions);
    }
}
