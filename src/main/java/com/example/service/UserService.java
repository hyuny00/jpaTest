package com.example.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.component.CustomObjectMapper;
import com.example.db1.entity.User1;
import com.example.db1.repository.User1Repository;
import com.example.db1.repository.UserJpqlRepository;
import com.example.dto.UserDto;
import com.example.util.ConvertUtil;
import com.example.util.FtMap;

@Service
public class UserService {

	@Autowired
	private User1Repository userRepository;

	@Autowired
	private UserJpqlRepository userJpqlRepository;

	@Autowired
	private  CustomObjectMapper customObjectMapper;



	public User1 TestA(FtMap param) throws IllegalAccessException, InvocationTargetException {


		User1 user = new User1();

		user = customObjectMapper.mapToEntity(param, User1.class);
		//ConvertUtil.mapToBean(user, param);


		return userRepository.save(user);
	}

	public User1 createUserInDb(String name) {
		User1 user = new User1();
		user.setName(name);
		user.setAge(29);
		return userRepository.save(user);
	}

	public User1 getUserFromDb(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public List<UserDto> getUserFromDb2(String name, int age) {

		return userJpqlRepository.getUsers(name, age);
	}


	public List<FtMap> findUserByIdAsCustomMap(int id) {

		return userRepository.findUserByIdAsCustomMap((long) id);
	}

	/*
	public List<FtMap> getUsers2(FtMap param) throws IllegalAccessException {

		param.put("name",  "%" + param.getString("name") + "%");
    	param = ConvertUtil.convertMapToObjectFieldType(param, User1.class);

    	System.out.println("..11111111111...................."+param.get("age"));

    	List<User1> list= userJpqlRepository.getUsers2(param);

		return ConvertUtil.listToObjectMaps(list);

	}
	*/
	public List<FtMap> getUsers2(FtMap param) throws IllegalAccessException {

    	param = ConvertUtil.convertMapToObjectFieldType(param, User1.class);


    	List<User1> list= userJpqlRepository.getUsers2(param);

        List<FtMap> listMap = list.stream()
                .map(user -> customObjectMapper.entityToMap(user))
                .collect(Collectors.toList());


		return listMap;

	}


}
/*
 * @GetMapping("/api/v3/simple-orders") public List<SimpleOrderDto> orderV3(){
 * List<Order> orders = orderRepository.findAllWithMemberDelivery();
 * List<SimpleOrderDto> result = orders.stream() .map(o -> new
 * SimpleOrderDto(o)) .collect(Collectors.toList()); return result; }
 */