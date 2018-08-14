package com.learn.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.annotation.Document;
import com.learn.domain.PersonBean;
import com.learn.query.DeleteQuery;
import com.learn.query.QueryIndex;
import com.learn.query.UpdateQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author :lwy
 * @date 2018/6/14 18:00
 */
public class TestCase {

    private static TransportClient client;

    static {
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "etouch-analysis").build();
        client = TransportClient.builder().settings(settings).build();
        String node = "node201.bigdata.dmp.local.com:23100";
        String hostName = StringUtils.substringBeforeLast(node, ":");
        String port = StringUtils.substringAfterLast(node, ":");

        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), NumberUtils.toInt(port)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        client.connectedNodes();
    }

    public static void main(String[] args) {


        TestCase testCase = new TestCase();
        //插入数据
        testCase.execute();

        //更新数据
        //testCase.query();

        //删除数据
        //testCase.delete();
        System.err.println(client);
    }

    //删除ES中的数据
    private void delete() {

        DeleteQuery deleteQuery = DeleteQuery.newBuilder()
                .withIndexName("person")
                .withType("man")
                .withId("20")
                .build();
        deleteInfo(deleteQuery);
    }

    private void deleteInfo(DeleteQuery deleteQuery) {
        String indexName = getIndexFormDocument(deleteQuery.getObject());
        if (StringUtils.equals("", indexName)) {
            indexName = deleteQuery.getIndexName();
        }
        String indexType = getTypeFromDocument(deleteQuery.getObject());

        if (StringUtils.equals("", indexType)) {
            indexType = deleteQuery.getType();
        }
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        //构建更新
        deleteIndex(indexName, indexType, deleteQuery, bulkRequestBuilder);
    }

    private void deleteIndex(String indexName, String indexType, DeleteQuery deleteQuery, BulkRequestBuilder bulkRequestBuilder) {
        DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete(indexName, indexType, deleteQuery.getId());
        bulkRequestBuilder.add(deleteRequestBuilder);
        BulkResponse response = bulkRequestBuilder.execute().actionGet();
        System.out.println("delete : " + response);
    }

    //查询数据
    private void query() {
        PersonBean personBean = new PersonBean();
        personBean.setName("bosh");

        UpdateQuery updateQuery = UpdateQuery.newBuilder()
                .withIndexName("person")
                .withType("man")
                .withId("20")
                .withObject(personBean)
                .build();
        updateIndex(updateQuery);
    }

    private void updateIndex(UpdateQuery updateQuery) {
        String indexName = getIndexFormDocument(updateQuery.getObject());
        if (StringUtils.equals("", indexName)) {
            indexName = updateQuery.getIndexName();
        }
        String indexType = getTypeFromDocument(updateQuery.getObject());

        if (StringUtils.equals("", indexType)) {
            indexType = updateQuery.getType();
        }
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        //构建更新
        updateInfo(indexName, indexType, updateQuery, bulkRequestBuilder);
    }

    private void updateInfo(String indexName, String indexType, UpdateQuery updateQuery, BulkRequestBuilder bulkRequestBuilder) {

        UpdateRequestBuilder updateRequestBuilder = null;
        if (Objects.nonNull(updateQuery.getObject()) && isDocument(updateQuery.getObject().getClass())) {
            updateRequestBuilder = client.prepareUpdate(indexName, indexType, updateQuery.getId())
                    .setDoc(JSON.toJSONString(updateQuery.getObject(), SerializerFeature.WriteDateUseDateFormat))
                    .setDocAsUpsert(true);
        }

        bulkRequestBuilder.add(updateRequestBuilder);
        BulkResponse response = bulkRequestBuilder.execute().actionGet();
        System.err.print("update: " + response);
    }

    private void execute() {
        PersonBean personBean = new PersonBean();
        personBean.setId(10);
        personBean.setName("bosh");

        QueryIndex queryIndex = QueryIndex.newBuilder()
                .withIndexName("person")
                .withType("man")
                .withId(personBean.getId().toString())
                .withObject(personBean)
                .build();
        prepareIndex(queryIndex);
    }

    private void prepareIndex(QueryIndex queryIndex) {

        String indexName = getIndexFormDocument(queryIndex.getObject());
        if (StringUtils.equals("", indexName)) {
            indexName = queryIndex.getIndexName();
        }
        String indexType = getTypeFromDocument(queryIndex.getObject());

        if (StringUtils.equals("", indexType)) {
            indexType = queryIndex.getType();
        }


        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        //构建查询
        indexQuery(queryIndex, indexName, indexType, bulkRequestBuilder);
        System.out.println(indexName + " : " + indexType);
    }

    private void indexQuery(QueryIndex queryIndex, String indexName, String indexType, BulkRequestBuilder bulkRequestBuilder) {

        IndexRequestBuilder indexRequestBuilder = null;
        if (Objects.nonNull(queryIndex.getObject()) && isDocument(queryIndex.getObject().getClass())) {

            //序列化操作
            //小试牛刀
            indexRequestBuilder = client.prepareIndex(indexName, indexType, queryIndex.getId()).setSource(JSON.toJSONString(queryIndex.getObject(), SerializerFeature.WriteMapNullValue));
            //indexRequestBuilder = client.prepareIndex(indexName, indexType, queryIndex.getId().toString()).setSource(queryIndex.getObject());
        }
        bulkRequestBuilder.add(indexRequestBuilder);
        BulkResponse response = bulkRequestBuilder.execute().actionGet();
        System.err.print(response);
    }

    private boolean isDocument(Class<?> aClass) {
        return aClass.isAnnotationPresent(Document.class);
    }

    private String getTypeFromDocument(Object object) {
        if (Objects.isNull(object)) {
            return "";
        }
        if (object.getClass().isAnnotationPresent(Document.class)) {
            Document clz = object.getClass().getAnnotation(Document.class);
            return clz.type();
        } else {
            return "";
        }
    }

    private String getIndexFormDocument(Object object) {
        if (Objects.isNull(object)) {
            return "";
        }
        if (object.getClass().isAnnotationPresent(Document.class)) {
            Document clz = object.getClass().getAnnotation(Document.class);
            return clz.indexName();
        } else {
            return "";
        }
    }













}
