package com.example.demo.router;

import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.handler.UserHandler;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;

public class Server {

	// 创建router路由
	public RouterFunction<ServerResponse> routingFunction() {
		UserService userService = new UserServiceImpl();
		UserHandler userHandler = new UserHandler(userService);
		RequestPredicate predicate = null;
		RouterFunctions.route(predicate, userHandler::getUserById);
		return null;

	}

}
