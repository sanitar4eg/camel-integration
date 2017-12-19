package com.example.application.repository;

import com.example.application.model.Envelope;
import com.example.application.model.Student;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

@Repository
@Slf4j
public class StudentRepository {

	private static final String CATALOG_PERSON = "http://localhost/1c-server/odata/standard.odata/Catalog_ФизЛица?$format=json";

	@Autowired
	private RestOperations restTemplate;

	public List<Student> findAll() {
		Envelope envelope = restTemplate
			.getForObject(CATALOG_PERSON, Envelope.class);

		log.info("Students: {}", envelope.getValue());

		return envelope.getValue();
	}

	public void delete(Student student) {
		restTemplate.delete(String
			.format("http://localhost/1c-server/odata/standard.odata/Catalog_ФизЛица(guid'%s')", student.getRefKey()));
	}

	public Student save(Student student) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Student> entity = new HttpEntity<>(student, headers);

		return restTemplate.postForObject(CATALOG_PERSON, entity, Student.class);
	}
}
