package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// 交给spring管理并返回json
@RestController
public class UserController {

	// 注入service
	@Autowired
	private UserService userService;

	// id查询
	@GetMapping("/user/{id}")
	public Mono<User> getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	// 查询所有
	@GetMapping("/queryUserList")
	public Flux<User> queryUserList() {
		return userService.queryUserList();
	}

	// 添加
	@PostMapping("/addUser")
	public Mono<Void> add(@RequestBody User user) {
		return userService.saveUser(Mono.just(user));
	}

}
