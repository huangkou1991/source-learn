package com.learn.builder;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

/**
 * @Author :lwy
 * @Date : 2018/8/27 15:31
 * @Description :
 */
public class AvroSchemaBuilder {

    public Schema createAvroHttpRequestSchema() {
        Schema clientIdentifier = SchemaBuilder.record("ClientIdentifier")
                .namespace("com.learn.avro.model")
                .fields()
                .requiredString("hostName")
                .requiredString("ipAddress")
                .endRecord();
        return SchemaBuilder.record("AvroHttpRequest")
                .namespace("com.learn.avro.model")
                .fields()
                .requiredLong("requestTime")
                .name("clientIdentifier").type(clientIdentifier).noDefault()
                .name("employeeNames").type().array().items().stringType().arrayDefault(null)
                .name("active").type().enumeration("Active").symbols("YES", "NO").noDefault()
                .endRecord();
    }
}

/**
 * 当我们完成生成模式时，让我们继续探索序列化部分。
 * <p>
 * Avro支持两种数据序列化格式：JSON格式和二进制格式。
 * <p>
 * 首先，我们将重点介绍JSON格式，然后我们将讨论二进制格式。
 * <p>
 * 在继续之前，我们应该通过几个关键接口。我们可以使用下面的接口和类进行序列化：
 * <p>
 * DatumWriter <T>：我们应该使用它来在给定的Schema上写入数据。我们将在我们的示例中使用  SpecificDatumWriter实现，但是，DatumWriter也有其他实现。其他实现是  GenericDatumWriter，Json.Writer，ProtobufDatumWriter，ReflectDatumWriter，ThriftDatumWriter。
 * <p>
 * 编码器：使用编码器或定义前面提到的格式。EncoderFactory提供两种类型的编码器，二进制编码器和JSON编码器。
 * <p>
 * DatumReader <D>：用于反序列化的单个接口。同样，它有多个实现，但我们将在我们的示例中使用SpecificDatumReader。其他实现是GenericDatumReader，Json.ObjectReader，Json.Reader，ProtobufDatumReader，ReflectDatumReader，ThriftDatumReader。
 * <p>
 * 解码器：在对数据进行反序列化时使用解码器。Decoderfactory提供两种类型的解码器：二进制解码器和JSON解码器。
 * <p>
 * 接下来，让我们看看Avro中是如何进行序列化和反序列化的。
 */
