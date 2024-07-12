package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.db1.entity.User1;
import com.example.db1.repository.User1Repository;
import com.example.db1.repository.UserJpqlRepository;
import com.example.dto.UserDto;

@Service
public class UserService {
	
	@Autowired
	private  User1Repository userRepository;
	
	@Autowired
	private UserJpqlRepository userJpqlRepository;

   
    
    public User1 createUserInDb(String name) {
        User1 user = new User1();
        user.setName(name);
        user.setAge(29);
        return userRepository.save(user);
    }

   

    public User1 getUserFromDb(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserDto>  getUserFromDb2(String name, int age) {
    	
    	return userJpqlRepository.getUsers(name, age);
    }


}
/*
@GetMapping("/api/v3/simple-orders")
public List<SimpleOrderDto> orderV3(){
    List<Order> orders = orderRepository.findAllWithMemberDelivery();
    List<SimpleOrderDto> result = orders.stream()
            .map(o -> new SimpleOrderDto(o))
            .collect(Collectors.toList());
    return result;
}
*/