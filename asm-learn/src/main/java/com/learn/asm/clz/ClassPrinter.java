package com.learn.asm.clz;


import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author :lwy
 * @date 2018/5/29 13:06
 * 打印类的属性
 */
public class ClassPrinter extends ClassVisitor {


    public ClassPrinter() {
        super(ASM4);
    }

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {
    }

    public void visitOuterClass(String owner, String name, String desc) {
    }

    public AnnotationVisitor visitAnnotation(String desc,
                                             boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {
    }

    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {

        System.err.println(" " + name + " " + outerName + " " + innerName);
    }

    public FieldVisitor visitField(int access, String name, String
            desc,
                                   String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }

}
