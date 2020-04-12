package com.springproject.ticketingsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springproject.ticketingsystem.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	@Query("select u from User u where u.email = :email")
	User findByEmail(@Param("email") String email);

	@Query("select u from User u where u.email = :email")
	Optional<User> findByEmailOptional(@Param("email") String email);

}