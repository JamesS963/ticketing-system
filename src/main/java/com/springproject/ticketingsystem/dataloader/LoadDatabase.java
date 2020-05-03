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

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(AuthorityDao authorityDao, UserDao userDao) {
		return args -> {

			authorityDao.save(new Authority(AuthorityType.ROLE_USER));
			authorityDao.save(new Authority(AuthorityType.ROLE_ADMIN));
			
			Authority admin = authorityDao.getByAuthority(AuthorityType.ROLE_ADMIN);
			Authority user = authorityDao.getByAuthority(AuthorityType.ROLE_USER);
			log.info("USER AUTH IS " + user.getAuthorityType());
			log.info("admin AUTH IS " + admin.getAuthorityType());
			log.info("USER ID IS " + user.getId());
			log.info("admin ID IS " + admin.getId());
			
			Set<Authority> adminSet = new HashSet<Authority>();
			adminSet.add(admin);

			Set<Authority> userSet = new HashSet<Authority>();
			userSet.add(user);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String password = encoder.encode("password");
			userDao.save(new User("Admin1@admin.com", adminSet, "firstname", "surname", password));
			userDao.save(new User("User@user.com", userSet, "firstname", "surname", password));
		};
	}
}