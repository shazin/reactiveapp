package com.shazin.github.reactive.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;

import com.shazin.github.reactive.app.domain.User;
import com.shazin.github.reactive.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)	
	public Observable<List<User>> find() {
		return userService.findAll().toList();
	}
	
	@RequestMapping(value="/name/{name}", method = RequestMethod.GET)
	public Observable<List<User>> findByName(@PathVariable String name) {
		return userService.findByName(name).toList();
	}
}
