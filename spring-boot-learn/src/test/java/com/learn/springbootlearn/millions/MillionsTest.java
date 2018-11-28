package com.learn.springbootlearn.millions;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.learn.springbootlearn.bloomFilter.BloomFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author :lwy
 * @Date : 2018/11/28 15:27
 * @Description :
 */
public class MillionsTest {

    //百亿数据命中测试
    @Test
    public void simpleTest() {
        long start = System.currentTimeMillis();

        Set<Integer> simpleSet = new HashSet<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            simpleSet.add(i);
        }
        Assert.assertTrue(simpleSet.contains(1));
        Assert.assertTrue(simpleSet.contains(2));
        Assert.assertTrue(simpleSet.contains(3));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
    }


    //自定义的bloomFilter测试
    @Test
    public void bloomfilter() {

        long star = System.currentTimeMillis();
        int size = 10000000;
        int[] array = new int[10000000];
        BloomFilter filter = new BloomFilter(size, array);

        for (int i = 0; i < 10000000; i++) {
            filter.add(i + "");
        }

        Assert.assertTrue(filter.check(1+""));
        Assert.assertTrue(filter.check(2+""));
        Assert.assertTrue(filter.check(3+""));
        Assert.assertTrue(filter.check(999999+""));
        Assert.assertFalse(filter.check(400230340+""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }


    //guava
    @Test
    public void guavaFilter(){
        long star = System.currentTimeMillis();
        com.google.common.hash.BloomFilter<Integer> filter=
                com.google.common.hash.BloomFilter.create(Funnels.integerFunnel()
                ,10000000,0.01);
        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }
        Assert.assertTrue(filter.mightContain(1));
        Assert.assertTrue(filter.mightContain(2));
        Assert.assertTrue(filter.mightContain(3));
        Assert.assertFalse(filter.mightContain(10000000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }

}
