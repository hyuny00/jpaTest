package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class QueryUtil {
	
	public static <T> TypedQuery<T> getQuery(EntityManager entityManager, String jpql, Map<String, Object> params, Class<T> resultClass) {
		
		System.out.println("jpql...................."+jpql);
		
        List<String> parameterList = extractParameters(jpql);

        TypedQuery<T> query = entityManager.createQuery(jpql, resultClass);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (parameterList.contains(entry.getKey())) {
                if (entry.getValue() != null) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }

        return query;
    }
	
	public static <T> Query getNativeQuery(EntityManager entityManager, String jpql, Map<String, Object> params, Class<T> resultClass) {
		
		System.out.println("jpql...................."+jpql);
		
        List<String> parameterList = extractParameters(jpql);

        Query query = entityManager.createNativeQuery(jpql,resultClass);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (parameterList.contains(entry.getKey())) {
                if (entry.getValue() != null) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }

        return query;
    }

    private static List<String> extractParameters(String jpql) {
        List<String> parameterList = new ArrayList<>();

        // Regular expression pattern to match ':word'
        Pattern pattern = Pattern.compile(":\\w+");
        Matcher matcher = pattern.matcher(jpql);

        while (matcher.find()) {
            String parameter = matcher.group().substring(1); // Remove the leading ':'
            parameterList.add(parameter);
        }

        return parameterList;
    }
    
  
}
