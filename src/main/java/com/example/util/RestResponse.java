package com.example.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

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
