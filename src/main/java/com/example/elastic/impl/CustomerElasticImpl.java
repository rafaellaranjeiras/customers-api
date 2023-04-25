package com.example.elastic.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.common.dto.CustomerElasticDto;
import com.example.elastic.AbstractElasticSearchClient;
import com.example.elastic.CustomerElastic;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerElasticImpl extends AbstractElasticSearchClient implements CustomerElastic {
	
	@Override
	public List<CustomerElasticDto> searchCustomerByName(String term, int page, int size) {
		try {			
			SearchResponse<CustomerElasticDto> search = client.search(s -> s
                    .index("search-customer")
                    .from(page * size)
                    .size(size)
                    .query(q -> q
                            .matchPhrasePrefix(t -> t
                                    .field("fullname")
                                    .query(term)
                            )
                    ),
                    CustomerElasticDto.class);

			return search.hits().hits().stream()
					.map(hit -> hit.source())
					.collect(Collectors.toList());
		} catch (ElasticsearchException | IOException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void addCustomer(CustomerElasticDto dto) {
		try {
			post("search-customer", dto, dto.getId().toString());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}		
	}


}
