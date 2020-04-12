package com.springproject.ticketingsystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "authority_id") })
	private Set<Authority> authorities;

	private String email;
	private String firstName;
	private String surname;
	private String password;

	public User() {
	};

	public User(String email, Set<Authority> authorities, String firstName, String surname, String password) {
		this.email = email;
		this.firstName = firstName;
		this.surname = surname;
		this.password = password;
	}

	public String getName() {
		return this.firstName + " " + this.surname;
	}
}
