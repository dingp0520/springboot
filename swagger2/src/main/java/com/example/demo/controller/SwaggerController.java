package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/hello")
@RestController
public class SwaggerController {

	@ApiOperation(value="say hello",notes="say hello")
	@RequestMapping(value="/say",method=RequestMethod.GET)
	public String sayHello() {
		return "hello";
	}
}
