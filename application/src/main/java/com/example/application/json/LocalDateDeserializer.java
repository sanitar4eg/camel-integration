package com.example.application.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	private static final long serialVersionUID = 1L;

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
		throws IOException, JsonProcessingException {
		return LocalDate.parse(jp.readValueAs(String.class), DateTimeFormatter.ISO_DATE_TIME);
	}
}
