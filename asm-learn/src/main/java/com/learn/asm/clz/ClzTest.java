package com.learn.asm.clz;

import org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * @author :lwy
 * @date 2018/5/29 13:10
 */
public class ClzTest {

    public static void main(String[] args) throws IOException {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.util.ArrayList");
        cr.accept(cp, 0);
    }
}
