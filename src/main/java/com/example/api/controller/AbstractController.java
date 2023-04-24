package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.RestResponse;
import com.example.util.StringUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public abstract class AbstractController implements ApplicationContextAware {
	
	protected ApplicationContext applicationContext;
	
	@Autowired
    private HttpServletRequest request;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	protected<T> T getService(Class<T> type) {
		String apiVersion = request.getAttribute("apiVersion").toString();
		String beanName = StringUtil.toCamelCase(type.getSimpleName()) + apiVersion;
		return (T) applicationContext.getBean(beanName);		
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
