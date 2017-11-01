package com.tvsee.transcode.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class RestTemplateUtil {
    
	@Resource
    private RestTemplate restTemplate;
    @Resource
	private ObjectMapper objectMapper;
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T post(String url, Map<String, ?> params, Class<T> type) {
        return request(url, HttpMethod.POST, params, type);
    }

    public <T> T get(String url, Map<String, ?> params, Class<T> type) {
        return request(url, HttpMethod.GET, params, type);
    }
    
    public <T> T delete(String url, Map<String, ?> params, Class<T> type) {
        return request(url, HttpMethod.DELETE, params, type);
    }
    
    public <T> T put(String url, Map<String, ?> params, Class<T> type) {
        return request(url, HttpMethod.PUT, params, type);
    }
    
    /**
     * @param req
     * @param url
     * @param method
     * @param params maybe null
     * @return
     */
    private <T> T request(String url, HttpMethod method, Map<String, ?> params, Class<T> type) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
        //获取header信息
        HttpHeaders requestHeaders = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
          String key = (String) headerNames.nextElement();
          String value = request.getHeader(key);
          requestHeaders.add(key, value);
          System.out.println(key+":"+value);
        }
        //获取parameter信息
        if(params == null) {
            params = request.getParameterMap();
        }
        
        @SuppressWarnings("rawtypes")
		HttpEntity requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> rss = restTemplate.exchange(url, method, requestEntity, String.class, params);
        String res = rss.getBody();
        if (StringUtils.isNotBlank(res)) {
        	try {
				return objectMapper.readValue(res, type);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return null;
    }
}