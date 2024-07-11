package com.example.db1.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.component.DynamicSqlProvider;
import com.example.db1.entity.User1;
import com.example.util.QueryUtil;

@Repository
public class UserJpqlRepository {
	
	@Autowired
    private EntityManager entityManager;

    @Autowired
    private DynamicSqlProvider sqlProvider;
    
    

    public List<User1> getUsers(String name, Integer age) {
        Map<String, Object> params = new HashMap<>();
        
        params.put("name",  "%" + name + "%");
        params.put("age", age);
        params.put("age22", age);
        
       // List likeParam= new ArrayList();
       // likeParam.add("age");
        

        String jpql = sqlProvider.getSql("com.example.queries.UserQueries.getUsersByName", params);
        
   
        TypedQuery<User1> query = QueryUtil.getQuery(entityManager, jpql, params, User1.class);
        
        
        
        /*
        if (name != null) {
            query.setParameter("name", "%" + name + "%");
        }
        if (age != null) {
            query.setParameter("age", age);
        }
    */
         
       
        return query.getResultList();
    }

}
