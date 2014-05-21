package br.com.ita.bdic3.controller.api;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class GenericController <T> {

	private Class<T> type;
	
	public GenericController(Class<T> type) {
		this.type = type;
	}

	private static final ObjectMapper mapper = new ObjectMapper();
	
	public T convertStringJsonToObject(String stringJson) throws JsonParseException, JsonMappingException, IOException{
		return mapper.readValue(stringJson, type);
	}
}