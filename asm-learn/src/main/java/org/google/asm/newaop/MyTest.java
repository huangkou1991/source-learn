package org.google.asm.newaop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author :lwy
 * @date 2018/7/5 18:40
 * http://www.shareniu.com/article/171.htm
 */
public class MyTest {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Shareniu c = new Shareniu();
        //c.m();

        //ClassLoader cl = MyTest.class.getClassLoader();
        //Class cls=cl.loadClass("E:\\workspace\\idea\\source-code\\Shareniu.class");
        //Shareniu shareniu= (Shareniu) cls.newInstance();
        //shareniu.m();

        //Class cls = Class.forName("E:\\workspace\\idea\\source-code\\Shareniu.class");

        byte[] cLassBytes = null;
        Path path;
        try {
            path = Paths.get(new URI("E:\\workspace\\idea\\source-code\\Shareniu.class"));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        //Class cLass = defineClass(cLassBytes, 0, cLassBytes.length);

    }

}
