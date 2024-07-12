package com.example.component;

import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicSqlProvider {

    
    @Autowired
    private  Configuration configuration;
    
    

    public String getQueryById(String queryId, Map<String, Object> parameters) {
        MappedStatement mappedStatement = configuration.getMappedStatement(queryId);
        BoundSql boundSql = mappedStatement.getBoundSql(parameters);
        return boundSql.getSql();
    }
    
}