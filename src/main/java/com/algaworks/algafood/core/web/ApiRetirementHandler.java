package com.algaworks.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiRetirementHandler implements HandlerInterceptor {

	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getRequestURI().startsWith("/v2/")) {
//			response.addHeader("X-Algafood-Deprecated", 
//					"Esta versão da API está depreciada e deixará de existir à "
//					+ "partir 01/07/2023. Use a versão mais atual da API.");
			response.setStatus(HttpStatus.GONE.value());
			return false;
		}
		
		return true;
	}
}
