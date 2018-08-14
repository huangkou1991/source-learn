package com.learn.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * @author :lwy
 * @date 2018/7/6 12:56
 */
public class ASMAop {
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("com.learn.asm.Operation");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cr.accept(new LogClassVisitor(cw), ClassReader.SKIP_DEBUG);

        Class<?> clazz = new MyClassLoader().defineClassForName("com.learn.asm.Operation", cw.toByteArray());

        clazz.getMethods()[0].invoke(clazz.newInstance());
    }

}

class MyClassLoader extends ClassLoader {
    public MyClassLoader() {
        super(Thread.currentThread().getContextClassLoader());
    }

    public Class<?> defineClassForName(String name, byte[] data) {
        return this.defineClass(name, data, 0, data.length);
    }
}
