package com.example.api.config;

import java.util.NoSuchElementException;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.api.controller.AbstractController;
import com.example.util.RestResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionHandling extends AbstractController {
	
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
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public RestResponse<Void> handleExceptions(NoHandlerFoundException ex) {
		return getRestResponse("Not found.");
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchBeanDefinitionException.class)
	public RestResponse<Void> handleExceptions(NoSuchBeanDefinitionException ex) {
		return getRestResponse("Not found.");
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthenticationException.class)
	public RestResponse<Void> handleExceptions(AuthenticationException ex) {
		return getRestResponse();
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

}
