package com.springproject.ticketingsystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

import com.springproject.ticketingsystem.model.User;

@Controller
public class AuthenticationController {

	// @Autowired
	// private UserService userService;

	@GetMapping(value = "/login")
	public ModelAndView getLoginPage() {
		return new ModelAndView("login", "user", new User());
	}

	@GetMapping(value = "/loginFailed")
	public ModelAndView loginFails(HttpServletRequest request) {
		return new ModelAndView("login", "user", new User()).addObject("error",
				"Login Credentials are incorrect, please try again.");
	}

}
