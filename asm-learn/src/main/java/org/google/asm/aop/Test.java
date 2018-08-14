package org.google.asm.aop;

import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.ASM6;

/**
 * @author :lwy
 * @date 2018/7/5 18:28
 * https://my.oschina.net/u/1166271/blog/162796
 */
public class Test {

    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
//
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("org/google/asm/aop/TestBean.class");
        ClassReader reader = new ClassReader(is);
        reader.accept(new AopClassAdapter(ASM6, cw), ClassReader.SKIP_DEBUG);
//
        byte[] code = cw.toByteArray();
    }
}

class AopClassAdapter extends ClassVisitor implements Opcodes {
    public AopClassAdapter(int api, ClassVisitor cv) {
        super(api, cv);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //更改类名，并使新类继承原有的类。
        super.visit(version, access, name + "_Tmp", signature, name, interfaces);
        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ("<init>".equals(name))
            return null;
        if (!name.equals("halloAop"))
            return null;
        //
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        return new AopMethod(this.api, mv);
    }
}

class AopMethod extends MethodVisitor implements Opcodes {
    public AopMethod(int api, MethodVisitor mv) {
        super(api, mv);
    }

    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "org/google/asm/aop/AopInterceptor", "beforeInvoke", "()V");
    }

    public void visitInsn(int opcode) {
        if (opcode == RETURN) {
            mv.visitMethodInsn(INVOKESTATIC, "org/google/asm/aop/AopInterceptor", "afterInvoke", "()V");
        }
        super.visitInsn(opcode);
    }
}
