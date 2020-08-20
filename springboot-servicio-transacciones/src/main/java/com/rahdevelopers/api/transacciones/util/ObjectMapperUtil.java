package com.rahdevelopers.api.transacciones.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class ObjectMapperUtil {

	@Autowired
	private ObjectMapper mapper;

	public <T> T entityToDto(Class<T> dto, Object entity) {
		try {
			String dtoString = this.mapper.writeValueAsString(entity);
			return (T) this.mapper.readValue(dtoString, dto);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public <T> T dtoToEntity(Class<T> entity, Object dto) {
		try {
			//solution to: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class
			this.mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			String entityString = this.mapper.writeValueAsString(dto);
			return (T) this.mapper.readValue(entityString, entity);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
