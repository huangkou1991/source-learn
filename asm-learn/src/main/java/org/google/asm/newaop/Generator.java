package org.google.asm.newaop;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author :lwy
 * @date 2018/7/5 18:40
 */
public class Generator {
    public static void main(String[] args) throws Exception{
        ClassReader cr = new ClassReader("org/google/asm/newaop/Shareniu");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new MyClassVisitor(cw);
        cr.accept(cv,ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
//输出
        File f = new File("E:\\workspace\\idea\\source-code\\Shareniu.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");
    }
}
