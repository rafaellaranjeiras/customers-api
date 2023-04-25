package com.example.elastic;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
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
		RestClientBuilder rest = RestClient.builder(
				new HttpHost(elasticHost, 443, "https"));		
		Header[] header =
			    new Header[]{new BasicHeader("Authorization",
			        "ApiKey " + apiKey)};
		rest.setDefaultHeaders(header);
		ElasticsearchTransport transport = new RestClientTransport(
				rest.build(), new JacksonJsonpMapper());
		client = new ElasticsearchClient(transport);
	}
	
	protected void post(String index, Object object, String id) throws IOException {
		client.index(i -> i
				.index(index)
				.id(id)
				.document(object));
		
	}

}
