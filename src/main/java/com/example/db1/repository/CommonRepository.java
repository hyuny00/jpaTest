package com.example.db1.repository;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


@Repository
public class CommonRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	public Timestamp getDbTime() {
        Timestamp dbTime = (Timestamp) entityManager.createNativeQuery("SELECT CURRENT_TIMESTAMP").getSingleResult();
        return dbTime;
    }
}
