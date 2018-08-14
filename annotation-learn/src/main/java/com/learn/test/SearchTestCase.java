package com.learn.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.domain.PersonBean;
import com.learn.query.SearchQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author :lwy
 * @date 2018/6/15 16:17
 */
public class SearchTestCase {
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



    private void query() {
        PersonBean personBean = new PersonBean();
        personBean.setName("wade");


        //构建数据
        QueryBuilder queryBuilder = QueryBuilders.termQuery("name", "wade");



        /*SearchQuery searchQuery=SearchQuery.newBuilder()
                .withIndexName("person")
                .withIndexType("man")
                .build();*/

        //searchFunction(queryBuilder);
        testSearch1();
    }

    public void testSearch1() {
        SearchRequestBuilder searchQuery = client.prepareSearch(indics)    // 在prepareSearch()的参数为索引库列表，意为要从哪些索引库中进行查询
                .setSearchType(SearchType.DEFAULT)  // 设置查询类型，有QUERY_AND_FETCH  QUERY_THEN_FETCH  DFS_QUERY_AND_FETCH  DFS_QUERY_THEN_FETCH
                .setQuery(QueryBuilders.termQuery("url", "redis.cn"))// 设置相应的query，用于检索，termQuery的参数说明：name是doc中的具体的field，value就是要找的具体的值
                ;
        // 如果上面不加查询条件，则会查询所有
        SearchResponse response = searchQuery.get();

        showResult(response);
    }

    /**
     * 格式化输出查询结果
     *
     * @param response
     */
    private void showResult(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        float maxScore = searchHits.getMaxScore();  // 查询结果中的最大文档得分
        System.out.println("maxScore: " + maxScore);
        long totalHits = searchHits.getTotalHits(); // 查询结果记录条数
        System.out.println("totalHits: " + totalHits);
        SearchHit[] hits = searchHits.getHits();    // 查询结果
        System.out.println("当前返回结果记录条数：" + hits.length);
        for (SearchHit hit : hits) {
            long version = hit.version();
            String id = hit.getId();
            String index = hit.getIndex();
            String type = hit.getType();
            float score = hit.getScore();
            System.out.println("===================================================");
            String source = hit.getSourceAsString();
            System.out.println("version: " + version);
            System.out.println("id: " + id);
            System.out.println("index: " + index);
            System.out.println("type: " + type);
            System.out.println("score: " + score);
            System.out.println("source: " + source);
        }
    }

    //json格式添加数据
    public void testAddJSON() {
        String source = "{\"name\":\"sqoop\", \"author\": \"apache\", \"version\": \"1.4.6\"}";
        IndexResponse response = client.prepareIndex("person", "man", "4").setSource(source).get();
        System.out.println(response.isCreated());
    }

    //map形式
    public void testAddMap() {
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("name", "flume");
        source.put("author", "Cloudera");
        source.put("version", "1.8.0");
        IndexResponse response = client.prepareIndex("person", "man", "5").setSource(source).get();
        System.out.println(response.isCreated());
    }

    /**
     * 添加数据：
     * 3.Java Bean方式
     *
     * 如果不将对象转换为json字符串，则会报下面的异常：
     * The number of object passed must be even but was [1]
     */
    public void testAddObj() throws JsonProcessingException {
        PersonBean product = new PersonBean();
        product.setName("ww");
        product.setId(34);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);
        System.out.println(json);
        IndexResponse response = client.prepareIndex("person", "man", "6").setSource(json).get();
        System.out.println(response.isCreated());
    }


    /**
     * 添加数据：
     * 4.XContentBuilder方式
     */
    public void testAddXContentBuilder() throws IOException {
        XContentBuilder source = XContentFactory.jsonBuilder();
        source.startObject()
                .field("name", "redis")
                .field("author", "redis")
                .field("version", "3.2.0")
                .field("url", "redis.cn")
                .endObject();
        IndexResponse response = client.prepareIndex("person", "man", "7").setSource(source).get();
        System.out.println(response.isCreated());
    }


    //

    public static void main(String[] args) throws Exception {

        SearchTestCase searchTestCase = new SearchTestCase();
        //构建查询
        //searchTestCase.query();

        //searchTestCase.testAddJSON();
        //searchTestCase.testAddMap();
        //searchTestCase.testAddObj();
        //searchTestCase.testAddXContentBuilder();
        searchTestCase.testQuery();
        searchTestCase.testUpdate();

        searchTestCase.testSearch1();
    }
    //查询结果
    public void testQuery(){
        GetResponse response=client.prepareGet("person","man","4").get();
        Map<String,Object> maps=response.getSourceAsMap();

        maps.forEach((x,y)->System.out.println(x+" : "+y));
    }
    /**
     * 局部更新操作与curl的操作是一致的
     * curl -XPOST http://uplooking01:9200/bigdata/product/AWA184kojrSrzszxL-Zs/_update -d' {"doc":{"name":"sqoop", "author":"apache"}}'
     *
     * 做全局更新的时候，也不用prepareUpdate，而直接使用prepareIndex
     */
    public void testUpdate() throws Exception {
    /*String source = "{\"doc\":{\"url\": \"http://flume.apache.org\"}}";
        UpdateResponse response = client.prepareUpdate(index, type, "4").setSource(source.getBytes()).get();*/
        // 使用下面这种方式也是可以的
        String source = "{\"name\": \"http://flume.apache.org\"}";
        UpdateResponse response = client.prepareUpdate("person","man", "4").setDoc(source.getBytes()).get();
        System.out.println(response.getVersion());
    }


    private String[] indics = {"person", "bank"};
}
