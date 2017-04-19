package com.duyngoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ehome")
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Hello World!";
	}
}
