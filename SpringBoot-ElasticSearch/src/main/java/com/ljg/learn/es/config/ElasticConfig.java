package com.ljg.learn.es.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.util.List;

@Slf4j
@Configuration
public class ElasticConfig extends AbstractElasticsearchConfiguration {

    @Value("${elastic.address}")
    private List<String> addressList;
    @Value("${elastic.username}")
    private String username;
    @Value("${elastic.password}")
    private String password;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        HttpHost[] httpHosts = new HttpHost[addressList.size()];
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        for (int i = 0; i < addressList.size(); i++) {
            String address = addressList.get(i);
            log.info("create elastic host:{}", address);
            httpHosts[i] = HttpHost.create(address);
        }

        return new RestHighLevelClient(RestClient.builder(httpHosts)
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                    httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    return httpAsyncClientBuilder;
                }
        ));
    }
}
