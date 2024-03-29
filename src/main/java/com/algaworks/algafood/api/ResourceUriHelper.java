package com.algaworks.algafood.api;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResourceUriHelper {

	public static void addUriInResponseHeader(Object resourceId) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resourceId)
				.toUri();
	
			HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getResponse();
			httpServletResponse.setHeader(HttpHeaders.LOCATION, uri.toString());
	}
}
