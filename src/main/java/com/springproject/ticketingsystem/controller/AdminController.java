package com.springproject.ticketingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
@GetMapping("/adminHome")
public ModelAndView getAdminHomePage() {
	return new ModelAndView("adminHome");
}
}
