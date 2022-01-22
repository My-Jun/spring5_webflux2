package com.example.demo.handler;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHandler {

	private final UserService userService;

	public UserHandler(UserService userService) {
		this.userService = userService;
	}

	// id查询
	public Mono<ServerResponse> getUserById(ServerRequest request) {
		// 获取id值
		String pathVariable = request.pathVariable("id");
		// 空值处理
		Mono<ServerResponse> noFound = ServerResponse.notFound().build();
		Integer id = Integer.valueOf(pathVariable);
		// 调用接口返回得到数据
		Mono<User> userMono = this.userService.getUserById(id);
		// 把userMono进行转换返回,使用Reactor操作符flatmap
		MediaType contentType = new MediaType("application", "json");
		return userMono.flatMap(person -> ServerResponse.ok().contentType(contentType)
				.body((BodyInserter<?, ? super ServerHttpResponse>) (person))).switchIfEmpty(noFound);
	}

}
