package org.google.asm.newaop;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author :lwy
 * @date 2018/7/5 18:39
 */
public class MyClassVisitor extends ClassVisitor implements Opcodes {
    public MyClassVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
                exceptions);
        if (!name.equals("<init>") && mv != null) {
// 为方法增加计时的功能
            mv = new MyMethodVisitor(mv);
        }
        return mv;
    }

    class MyMethodVisitor extends MethodVisitor implements Opcodes {
        public MyMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitCode() {
            /*1.首先调用父类的，然后增加自身的处理逻辑
             * 2.增加long a1 = System.currentTimeMillis();
             * */
            super.visitCode();
//获取到currentTimeMillis并压入到本地栈
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitVarInsn(LSTORE, 1);

        }

        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                    || opcode == Opcodes.ATHROW) {
//方法在返回之前，加入long a2 = System.currentTimeMillis();
//System.out.println("MyTimeLog now use time===="+(a2-a1));

                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                mv.visitVarInsn(LSTORE, 3);
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
                mv.visitInsn(DUP);
                mv.visitLdcInsn("MyTimeLog now use time====");
                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
                mv.visitVarInsn(LLOAD, 3);
                mv.visitVarInsn(LLOAD, 1);
                mv.visitInsn(LSUB);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            }
            mv.visitInsn(opcode);
        }
    }
}
