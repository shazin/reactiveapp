package com.shazin.github.reactive.app.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String greet() {
		return "Hello "+LocalDate.now();
	}
	
}
