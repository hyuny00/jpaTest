package com.example.component;

import org.springframework.stereotype.Component;

import com.example.util.FtMap;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomObjectMapper extends ObjectMapper {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public <T> T mapToEntity(FtMap ftMap, Class<T> targetType) {
        return convertValue(ftMap, targetType);
    }

    public <T>  FtMap entityToMap(T source) {
        return convertValue(source, FtMap.class);
    }

    /*
     *   public <T> T mapToEntity(Object source, Class<T> targetType) {
        return objectMapper.convertValue(source, targetType);
    }

    public <T> Object entityToMap(T source) {
        return objectMapper.convertValue(source, Object.class);
    }
    */

}
