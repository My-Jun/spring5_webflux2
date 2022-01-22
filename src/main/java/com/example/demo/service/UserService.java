package com.example.demo.service;

import com.example.demo.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


// 用户操作接口
public interface UserService {

	// 根据id查询用户
	// 因为查询所以用户是1个或0，所以使用Mono
	Mono<User> getUserById(int id);
	
	// 查询所有用户
	// 因为查询所以用户是多个，所以使用Flux
	Flux<User> queryUserList();
	
	//  返回 添加用户
	Mono<Void> saveUser(Mono<User> user);
	
}
