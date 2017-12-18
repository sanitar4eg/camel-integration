package com.example.integration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfiguration {

	@Bean
	public ActiveMQComponent activemq() {
		ActiveMQComponent component = new ActiveMQComponent();
		component.setBrokerURL("tcp://localhost:61616");
		return component;
	}

	@Bean
	public MemoryIdempotentRepository idempotentRepo() {
		return (MemoryIdempotentRepository) MemoryIdempotentRepository
			.memoryIdempotentRepository(200);
	}

//	@Bean
//	public Processor lookupUUID() {
//		return new DataObjectLookupProcessor();
//	}

//	@Bean
//	public ViewExc
//
//	<bean id="view" class="com.ipc.oce.testing.camel.ViewExchange" />

	@Bean
	public List<String> dataChangesLevels() {
		return Arrays.asList("_$Data$_.New", "_$Data$_.Update", "_$Data$_.Delete");
	}

	@Bean
	public List<String> objectList() {
		return Collections.singletonList("Document.СчетНаОплатуПокупателю");
	}

//	@Bean
//	public OCXEComponent ocx() {
//		OCXEComponent component = new OCXEComponent();
//		component.setHost("localhost");
//		component.setDbpath("C:\\workspace\\study\\1c");
//		component.setLogin("denis_mezhuev@epam.com");
//		component.setPassword("epaM7epaM");
//		component.setDbuser("");
//		component.setDbpassword("");
//		return component;
//	}
}

