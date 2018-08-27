package com.learn.avro.serealization;

import com.learn.avro.model.AvroHttpRequest;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author :lwy
 * @Date : 2018/8/27 16:32
 * @Description :
 */
public class AvroDeSerealizer {
    private static final Logger logger = LoggerFactory.getLogger(AvroDeSerealizer.class);

    public AvroHttpRequest deSerealizeAvroHttpRequestJSON(byte[] data) {
        DatumReader<AvroHttpRequest> reader = new SpecificDatumReader<>(AvroHttpRequest.class);

        Decoder decoder = null;
        try {
            decoder = DecoderFactory.get().jsonDecoder(AvroHttpRequest.getClassSchema(), new String(data));
            return reader.read(null, decoder);
        } catch (IOException e) {
            logger.error("Deserialization error" + e.getMessage());
        }
        return null;
    }

    public AvroHttpRequest deSerealizeAvroHttpRequestBinary(byte[] data) {
        DatumReader<AvroHttpRequest> employeeReader = new SpecificDatumReader<>(AvroHttpRequest.class);
        Decoder decoder = DecoderFactory.get()
                .binaryDecoder(data, null);
        try {
            return employeeReader.read(null, decoder);
        } catch (IOException e) {
            logger.error("Deserialization error" + e.getMessage());
        }
        return null;
    }
}
