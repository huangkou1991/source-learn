package com.learn.jute.learn;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.server.ByteBufferInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author :lwy
 * @date 2018/7/24 17:15
 */
public class Test {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //序列化工具
        BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);

        new MockReqHeader(0x342acede7463920l, "ping").serialize(boa, "header");

        //TCP网络传输对象
        ByteBuffer bb = ByteBuffer.wrap(baos.toByteArray());
        //开始反序列化
        ByteBufferInputStream bbios = new ByteBufferInputStream(bb);

        //反序列化工具
        BinaryInputArchive bbia = BinaryInputArchive.getArchive(bbios);

        MockReqHeader header = new MockReqHeader();
        header.deserialize(bbia, "header");
        bbios.close();
        baos.close();

        System.out.println(header.getType() + " ： " + header.getSessionId());
    }
}
