package com.example.application.repository;

import com.example.application.model.Envelope;
import com.example.application.model.Student;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
public class StudentRepository {

	private RestTemplate restTemplate = new RestTemplate();

	public List<Student> findAll() {
		Envelope envelope = restTemplate
			.getForObject("http://localhost/1c-server/odata/standard.odata/Catalog_ФизЛица?$format=json",
				Envelope.class);

		log.info("Students: {}", envelope.getValue());

		return envelope.getValue();
	}

}
