package com.learn.springbootlearn.bloomFilter;

/**
 * @Author :lwy
 * @Date : 2018/11/28 15:37
 * @Description :
 */
public class BloomFilter {


    /**
     * 长度
     */
    private int size;

    /**
     * 容器
     */
    private int[] array;


    public BloomFilter(int size, int[] array) {
        this.size = size;
        this.array = array;
    }

    /**
     * 写入数据
     */
    public void add(String key) {
        int hash1 = hashcode_1(key);
        int hash2 = hashcode_2(key);
        int hash3 = hashcode_3(key);

        array[hash1 % size] = 1;
        array[hash2 % size] = 1;
        array[hash3 % size] = 1;
    }

    /**
     * 判断是否存在
     */
    public boolean check(String key) {
        int hash1 = hashcode_1(key);
        int hash2 = hashcode_2(key);
        int hash3 = hashcode_3(key);

        if (array[hash1 % size] == 0) {
            return false;
        }

        if (array[hash2 % size] == 0) {
            return false;
        }

        if (array[hash3 % size] == 0) {
            return false;
        }

        return true;
    }


    /**
     * hash 算法1
     *
     * @param key
     * @return
     */
    private int hashcode_1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    /**
     * hash 算法2
     *
     * @param data
     * @return
     */
    private int hashcode_2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    /**
     * hash 算法3
     *
     * @param key
     * @return
     */
    private int hashcode_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }
}
