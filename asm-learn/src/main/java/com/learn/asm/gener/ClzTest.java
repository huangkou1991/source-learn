package com.learn.asm.gener;

import org.objectweb.asm.ClassWriter;

import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author :lwy
 * @date 2018/5/29 13:22
 */
public class ClzTest {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "com/learn/asm/gener/Comparable", null, "java/lang/Object",
                new String[]{"com/learn/asm/gener/Mesurable"});
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        MyClassLoader clzTest = new ClzTest().new MyClassLoader();
        Class c = clzTest.defineClass("com.learn.asm.gener.Comparable", b);
        Method[] methods = c.getMethods();
        System.err.println(methods[0]);
        System.out.println(c);
    }

    class MyClassLoader extends ClassLoader {
        public Class defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

    class StubClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if (name.endsWith("_Stub")) {
                ClassWriter cw = new ClassWriter(0);
                byte[] b = cw.toByteArray();
                return defineClass(name, b, 0, b.length);
            }
            return super.findClass(name);
        }
    }
}
