package com.springproject.ticketingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springproject.ticketingsystem.model.Authority;
import com.springproject.ticketingsystem.settings.AuthorityType;

@Repository
public interface AuthorityDao extends JpaRepository<Authority, Long> {

	@Query("select a from Authority a where a.authorityType = :authorityType")
	Authority getByAuthority(@Param("authorityType") AuthorityType authorityType);

}
