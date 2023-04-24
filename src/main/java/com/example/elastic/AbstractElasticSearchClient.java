package com.example.elastic;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.annotation.PostConstruct;

@Service
public abstract class AbstractElasticSearchClient {
	
	@Value("${elasticsearch.host}")
	private String elasticHost;
	
	@Value("${elasticsearch.api-key}")
	private String apiKey;
	
	protected ElasticsearchClient client;
	
	@PostConstruct
	private void setup() {
		RestClient restClient = RestClient.builder(
				new HttpHost(elasticHost, 9200)).build();
		ElasticsearchTransport transport = new RestClientTransport(
				restClient, new JacksonJsonpMapper());
		client = new ElasticsearchClient(transport);
	}
	
	protected void post(String index, Object object, String id) throws IOException {
		client.index(i -> i
				.index(index)
				.id(id)
				.document(object));
		
	}

}
