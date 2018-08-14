package com.learn.test;

import com.learn.lock.ZKLockUtil;

/**
 * @author :lwy
 * @date 2018/6/25 19:56
 */
public class LockTest {


    public static void main(String[] args) {


        LockTest lockTest = new LockTest();
        lockTest.execute();
    }

    private void execute() {


        try {
            ZKLockUtil.tryLock("testKey");
            /*for (int i = 0; i < 10; i++) {
                String nameSpace = ZKLockUtil.getClient().getNamespace();
                System.err.println(nameSpace);
            }*/
            System.out.println(23);
        } finally {
            ZKLockUtil.releaseLock("testKey");
        }
    }
}
