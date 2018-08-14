package com.learn.dozerspringboot.es.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author :lwy
 * @date 2018/6/2 8:29
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.learn.dozerspringboot.es.repository")
@ComponentScan(basePackages = {"com.learn.dozerspringboot.es.service"})
@PropertySource(value = "classpath:/application.properties")
public class Config {

    @Value("${es.host}")
    private String node;
    @Value("${es.port}")
    private String port;

    @Bean
    public TransportClient elasticsearchClient() {
        TransportClient transportClient = null;
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "etouch-analysis").build();
        try {
            transportClient = TransportClient.builder()
                    .settings(settings)
                    .build()
                    .addTransportAddress(
                            new InetSocketTransportAddress(InetAddress.getByName(node), Integer.valueOf(port)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return transportClient;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(elasticsearchClient());
    }
}
