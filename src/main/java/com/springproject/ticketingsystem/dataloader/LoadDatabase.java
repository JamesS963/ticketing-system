package com.springproject.ticketingsystem.dataloader;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springproject.ticketingsystem.dao.AuthorityDao;
import com.springproject.ticketingsystem.dao.UserDao;
import com.springproject.ticketingsystem.model.Authority;
import com.springproject.ticketingsystem.model.User;
import com.springproject.ticketingsystem.settings.AuthorityType;

@Configuration
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(AuthorityDao authorityDao, UserDao userDao) {
		return args -> {

			authorityDao.save(new Authority(AuthorityType.ROLE_USER));
			authorityDao.save(new Authority(AuthorityType.ROLE_ADMIN));

			Set<Authority> adminSet = new HashSet<Authority>();
			adminSet.add(authorityDao.getByAuthority(AuthorityType.ROLE_ADMIN));

			Set<Authority> userSet = new HashSet<Authority>();
			userSet.add((authorityDao.getByAuthority(AuthorityType.ROLE_USER)));

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String password = encoder.encode("password");

			userDao.save(new User("Admin1@admin.com", adminSet, "firstname", "surname", password));
			userDao.save(new User("User@user.com", userSet, "firstname", "surname", password));
		};
	}
}