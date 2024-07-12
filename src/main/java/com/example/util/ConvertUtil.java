package com.example.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertUtil {
	
	  
    public static void mapToBean(Object target, Map<String, Object> source) throws IllegalAccessException, InvocationTargetException {
		org.apache.commons.beanutils.BeanUtils.populate(target, source);
	}
    
    
    public static FtMap objectToMap(Object obj) throws IllegalAccessException {
    	FtMap map = new FtMap();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // Enable access to private fields
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
    
    
    public static List<FtMap> listToObjectMaps(List<?> list) throws IllegalAccessException {
        List<FtMap> listOfMaps = new ArrayList<>();

        for (Object obj : list) {
        	FtMap map = new FtMap();
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // Enable access to private fields
                map.put(field.getName(), field.get(obj));
            }
            listOfMaps.add(map);
        }

        return listOfMaps;
    }
    
    
    public static <T> FtMap convertMapToObjectFieldType(FtMap ftMap, Class<T> clazz) {
        try {
            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                if(ftMap.containsKey(fieldName)) {
                	  String stringValue = ftMap.getString(fieldName);
                      if (stringValue != null) {
                          Object value = convertToFieldType(stringValue, field.getType());
                          ftMap.put(fieldName, value);
                      }
                }
              
            }
            return ftMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object convertToFieldType(String stringValue, Class<?> fieldType) {
        if (fieldType == String.class) {
            return stringValue;
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(stringValue);
        } else if (fieldType == long.class || fieldType == Long.class) {
            return Long.parseLong(stringValue);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(stringValue);
        } else {
            throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        }
    }
}
