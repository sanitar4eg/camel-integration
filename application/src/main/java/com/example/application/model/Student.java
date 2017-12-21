package com.example.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	public LocalDate birthDay;
	@JsonProperty("Ref_Key")
	private String refKey;
	@JsonProperty("Ref")
	private String ref;
	@JsonProperty("DataVersion")
	private String dataVersion;
	@JsonProperty("DeletionMark")
	private Boolean deletionMark;
	@JsonProperty("Code")
	private Long code;
	@JsonProperty("Description")
	private String description;

}
