package com.learn.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author :lwy
 * @date 2018/7/6 12:58
 */
public class LogClassVisitor extends ClassVisitor {
    public LogClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ("oper".equals(name)) {
            return new LogMethodVisitor(super.visitMethod(access, name, desc, signature, exceptions));
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

}
