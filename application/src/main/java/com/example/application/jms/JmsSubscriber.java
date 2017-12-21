package com.example.application.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsSubscriber {

	@JmsListener(destination = "topic.Person", containerFactory = "jmsFactory")
	public StudentJmsEnvelope receive(StudentJmsEnvelope msg) {
		log.info("Recieved Message: " + msg);
		return msg;
	}

}
