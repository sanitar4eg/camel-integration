package com.example.application.jms;

import com.example.application.model.Student;
import com.example.application.ui.MainController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsSubscriber {

	@Autowired
	private MainController mainController;

	@JmsListener(destination = "topic.Person", containerFactory = "jmsFactory")
	public StudentJmsEnvelope receive(StudentJmsEnvelope msg) {
		log.info("Recieved Message: " + msg);
		Student student = msg.getStudent();
		student.setRefKey(student.getRef());
		mainController.addStudent(student);
		return msg;
	}

}
