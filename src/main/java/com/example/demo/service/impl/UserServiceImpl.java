package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserServiceImpl implements UserService {

	// 创建map 模拟数据库
	private final Map<Integer, User> t_user = new HashMap<Integer, User>();

	// 无参构造存入map数据库
	public UserServiceImpl() {
		this.t_user.put(1, new User("张三", "男", "12"));
		this.t_user.put(2, new User("小丽", "女", "11"));
	}

	@Override
	public Mono<User> getUserById(int id) {
		// 根据id查询用户
		return Mono.justOrEmpty(this.t_user.get(id));
	}

	@Override
	public Flux<User> queryUserList() {
		// 查询所有用户
		return Flux.fromIterable(this.t_user.values());
	}

	@Override
	public Mono<Void> saveUser(Mono<User> user) {
		// 便利内容
		return user.doOnNext(person -> {  
			// 向map集合添加
			int id = this.t_user.size() + 1;
			this.t_user.put(id, person); 
		}).thenEmpty(Mono.empty());// thenEmpty终止信号，清空Mono
	}

}
