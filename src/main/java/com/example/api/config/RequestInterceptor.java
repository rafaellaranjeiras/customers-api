package com.example.api.config;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String apiVersion;
		try {
			String uri = request.getServletPath();
			apiVersion = uri.split("/")[1].toUpperCase();
		} catch(Exception e) {
			apiVersion = "";
		}
        request.setAttribute("apiVersion", apiVersion);        
        return true;
    }

}
