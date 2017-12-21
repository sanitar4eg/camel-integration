package com.example.application.jms;

import com.example.application.model.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class StudentJmsEnvelope {

	@JsonProperty("#type")
	private String type;

	@JsonProperty("#value")
	private Student student;
}
