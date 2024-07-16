package com.example.controller;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.controller.AbstractController;
import com.example.db1.entity.User1;
import com.example.dto.UserDto;
import com.example.service.UserService;
import com.example.util.FtMap;

@RestController
@RequestMapping("/api/users")
public class UserController  extends AbstractController {

	@Autowired
    private  UserService userService;



    @PostMapping("/db1")
    public User1 createUserInDb(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

    	FtMap params = getFtMap(request);

        return userService.TestA(params);
    }



    @GetMapping("/db1/{id}")
    public User1 getUserFromDb(@PathVariable Long id) {
        return userService.getUserFromDb(id);
    }



    @GetMapping("/db1/{name}/{age}")
    public List<UserDto> getUserFromDb2(@PathVariable String name, @PathVariable int age) {
        return userService.getUserFromDb2(name, age);
    }

    @GetMapping("/db12/{id}")
    public List<FtMap> getUserFromDb(@PathVariable int id) {
        return userService.findUserByIdAsCustomMap(id);
    }

    @GetMapping("/db1234")
    public List<FtMap> getUsers2(HttpServletRequest request) throws IllegalAccessException {

    	FtMap params = getFtMap(request);
    	params.put("name", "홍");

    	System.out.println("......................"+params.get("age"));

        return userService.getUsers2(params);
    }


    @GetMapping("/db12345")
    public List<FtMap> getUsers3(HttpServletRequest request) throws IllegalAccessException {

    	FtMap params = getFtMap(request);
    	params.put("name", "홍");

    	System.out.println("......................"+params.get("age"));

        return userService.getUsers3(params);
    }
    
}




