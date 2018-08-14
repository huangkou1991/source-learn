package org.google.asm.newaop;

/**
 * @author :lwy
 * @date 2018/7/5 18:38
 */
public class Shareniu {
    public void m() {
        System.out.println("now in Shareniu.m==");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
