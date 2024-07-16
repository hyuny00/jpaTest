package com.example.db1.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.component.DynamicSqlProvider;
import com.example.db1.entity.User1;
import com.example.dto.UserDto;
import com.example.util.FtMap;
import com.example.util.QueryUtil;

@Repository
public class UserJpqlRepository {

	@PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DynamicSqlProvider sqlProvider;



    public List<UserDto> getUsers(String name, Integer age) {
        Map<String, Object> params = new HashMap<>();

        params.put("name",  "%" + name + "%");
        params.put("age", age);
       // params.put("age22", age);

       // List likeParam= new ArrayList();
       // likeParam.add("age");


        String jpql = sqlProvider.getQueryById("com.example.queries.UserQueries.getUsersByName", params);


        TypedQuery<UserDto> query = QueryUtil.getQuery(entityManager, jpql, params, UserDto.class);



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


    public List<User1> getUsers2(FtMap param) throws IllegalAccessException {

        String jpql = sqlProvider.getQueryById("com.example.queries.UserQueries.getUsersByName2", param);


        TypedQuery<User1> query = QueryUtil.getQuery(entityManager, jpql, param, User1.class);


        List<User1> gg= query.getResultList();


        return gg;
    }
    
    
    public List<User1> getUsers3(FtMap param) throws IllegalAccessException {

        String jpql = sqlProvider.getQueryById("com.example.queries.UserQueries.getUsersByName3", param);
        
        
        System.out.println("jpql........"+jpql);

        Query query = QueryUtil.getNativeQuery(entityManager, jpql, param, User1.class);


        List<User1> gg= query.getResultList(); 

 
        return gg;
    }


}
