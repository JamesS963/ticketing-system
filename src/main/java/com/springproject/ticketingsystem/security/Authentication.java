package com.springproject.ticketingsystem.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.springproject.ticketingsystem.model.User;

public class Authentication {

	AuthenticatedUserDetails user;

	public Authentication() {
		super();
		user = (AuthenticatedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public User getLoggedInUser() {

		return user.getAccount();
	}
}
