package com.learn;

import com.learn.avro.model.Active;
import com.learn.avro.model.AvroHttpRequest;
import com.learn.avro.model.ClientIdentifier;
import com.learn.avro.serealization.AvroDeSerealizer;
import com.learn.avro.serealization.AvroSerealizer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

/**
 * @Author :lwy
 * @Date : 2018/8/27 16:30
 * @Description :
 */
public class TestCase {


    AvroSerealizer serealizer;
    AvroDeSerealizer deSerealizer;
    AvroHttpRequest request;

    @Before
    public void setUp() throws Exception {
        serealizer = new AvroSerealizer();
        deSerealizer = new AvroDeSerealizer();

        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
                .setHostName("localhost")
                .setIpAddress("255.255.255.0")
                .build();

        List<CharSequence> employees = new ArrayList();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        request = AvroHttpRequest.newBuilder()
                .setRequestTime(01l)
                .setActive(Active.YES)
                .setClientIdentifier(clientIdentifier)
                .setEmployeeNames(employees)
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenSerialized_UsingJSONEncoder_ObjectGetsSerialized() {
        byte[] data = serealizer.serealizerAvrpHttpRequestJSON(request);
        assertTrue(Objects.nonNull(data));
        assertTrue(data.length > 0);

        //反序列化
        AvroHttpRequest request = deSerealizer.deSerealizeAvroHttpRequestJSON(data);
        System.err.println(request);
    }
}
