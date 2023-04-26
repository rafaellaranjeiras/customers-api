package com.example.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Response Metadata")
@Data
@JsonInclude(value = Include.NON_NULL)
public class RestResponse<T> {
	
	public RestResponse() {
		this.setTimestamp(new Date());		
	}
	
	private Date timestamp;
	private Integer page;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private String error;
	private T data;

}
