package com.example.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Customer search data")
@Data
public class CustomerElasticDto {
	
	private Long id;
	private String fullname;

}
