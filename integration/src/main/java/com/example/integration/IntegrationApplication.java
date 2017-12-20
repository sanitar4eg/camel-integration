package com.example.integration;

import javax.ws.rs.core.MediaType;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ImportResource({"classpath:camel-context.xml"})
public class IntegrationApplication {

	@Value("${server.port}")
	String serverPort;

	@Value("${application.path}")
	String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(IntegrationApplication.class, args);
	}

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(),
			contextPath + "/*");
		servlet.setName("CamelServlet");
		return servlet;
	}

	@Bean
	public ActiveMQComponent activeMQComponent() throws Exception {
		ActiveMQComponent activeMQComponent = new ActiveMQComponent();
		activeMQComponent.setBrokerURL("tcp://localhost:61616?broker.useJmx=true");
		activeMQComponent.start();
		return activeMQComponent;
	}

	@Component
	class RestApi extends RouteBuilder {

		@Override
		public void configure() throws Exception {

			CamelContext camelContext = new DefaultCamelContext();

			camelContext.addComponent("activemq", activeMQComponent());

			// http://localhost:8080/camel/api-doc
			restConfiguration().contextPath(contextPath)
				.port(serverPort)
				.enableCORS(true)
				.apiContextPath("/api-doc")
				.apiProperty("api.title", "Test REST API")
				.apiProperty("api.version", "v1")
				.apiProperty("cors", "true")
				.apiContextRouteId("doc-api")
				.component("servlet")
				.bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true");

			rest("/api/").description("Teste REST Service")
				.id("api-route")
				.post("/student")
				.produces(MediaType.APPLICATION_JSON)
				.consumes(MediaType.APPLICATION_JSON)
				.to("direct:remoteService");

			from("direct:remoteService")
				.routeId("direct-route")
				.tracing()
				.process(exchange -> exchange.getOut().setBody(exchange.getIn().getBody()))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
				.to("activemq:topic:topic.Person");
		}
	}
}
