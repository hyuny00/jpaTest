package com.example.db1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.db1.entity.User1;
import com.example.util.FtMap;

@Repository
public interface User1Repository extends JpaRepository<User1, Long> {


	@Query("SELECT u.id AS id, u.name AS name FROM User1 u WHERE u.id = :id")
	List<FtMap> findUserByIdAsCustomMap(@Param("id") Long id);



	//CustomMap 사용가능
	 //@Query("SELECT NEW aa.bb.CustomMap(u.id AS id, u.name AS name) FROM User u WHERE u.id = :userId")
	// List<CustomMap> findUserByIdAsCustomMap(@Param("userId") Long userId);

	/*
	  @Query(value = "SELECT id, name FROM users WHERE id = :userId", nativeQuery = true)
	    List<Map<String, Object>> findUserByIdAsMap(@Param("userId") Long userId);
	    */
}