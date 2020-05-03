package com.springproject.ticketingsystem.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproject.ticketingsystem.dao.AuthorityDao;
import com.springproject.ticketingsystem.dao.UserDao;
import com.springproject.ticketingsystem.model.User;
import com.springproject.ticketingsystem.security.AuthenticatedUserDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorityDao;

	BCryptPasswordEncoder encoder;

	public UserService() {

		super();
		encoder = new BCryptPasswordEncoder();
	}

	// we are using the email for the username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return new AuthenticatedUserDetails(user);
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public Optional<User> retrieveById(long id) {
		return userDao.findById(id);
	}

}