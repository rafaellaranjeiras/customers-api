package com.example.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.RestResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public abstract class AbstractController {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public RestResponse<Void> handleExceptions(IllegalArgumentException ex) {
		return getRestResponse(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public RestResponse<Void> handleExceptions(NoSuchElementException ex) {
		return getRestResponse("Not found.");
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(IllegalStateException.class)
	public RestResponse<Void> handleExceptions(IllegalStateException ex) {
		return getRestResponse(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public RestResponse<Void> handleExceptions(HttpRequestMethodNotSupportedException ex) {
		log.error(ex.getMessage(), ex);
		return getRestResponse(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public RestResponse<Void> handleExceptions(HttpMediaTypeNotSupportedException ex) {
		log.error(ex.getMessage(), ex);
		return getRestResponse(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public RestResponse<Void> handleExceptions(Exception ex) {
		log.error(ex.getMessage(), ex);
		return getRestResponse("Internal server error.");
	}
	
	protected<T> RestResponse<T> getRestResponse(T data) {
		RestResponse<T> restResponse = getRestResponse();
		restResponse.setData(data);
		return restResponse;
	}
	
	protected<T> RestResponse<List<T>> getRestResponse(Page<T> page) {
		RestResponse<List<T>> restResponse = getRestResponse(page.getContent());
		restResponse.setPage(page.getPageable().getPageNumber());
		restResponse.setPageSize(page.getPageable().getPageSize());
		restResponse.setTotalElements(page.getTotalElements());
		restResponse.setTotalPages(page.getTotalPages());
		return restResponse;
	}
	
	protected RestResponse<Void> getRestResponse(String error) {
		RestResponse<Void> restResponse = getRestResponse();
		restResponse.setError(error);
		return restResponse;
	}
	
	protected<T> RestResponse<T> getRestResponse() {
		return new RestResponse<>();
	}

}
