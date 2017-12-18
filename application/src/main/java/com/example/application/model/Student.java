package com.example.application.model;

import com.example.application.json.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
@ToString
public class Student {

	@JsonProperty("ДатаРождения")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate birthDay;
	@JsonProperty("Ref_Key")
	private String refKey;
	@JsonProperty("DataVersion")
	private String dataVersion;
	@JsonProperty("DeletionMark")
	private Boolean deletionMark;
	@JsonProperty("Code")
	private Long code;
	@JsonProperty("Description")
	private String description;

}
