package com.example.application.json;

import java.io.IOException;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
		throws IOException {
		ClientHttpResponse response = execution.execute(request, body);

		log.info(
			"request method: {}, request URI: {}, request headers: {}, request body: {}, response status code: {}, response headers: {}, response body: {}",
			request.getMethod(),
			request.getURI(),
			request.getHeaders(),
			new String(body, Charset.forName("UTF-8")),
			response.getStatusCode(),
			response.getHeaders(),
			new String(IOUtils.toByteArray(response.getBody()), Charset.forName("UTF-8")));

		return response;
	}

}
