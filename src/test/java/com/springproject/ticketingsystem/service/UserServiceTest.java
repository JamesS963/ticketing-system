package com.springproject.ticketingsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.springproject.ticketingsystem.dao.UserDao;
import com.springproject.ticketingsystem.model.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {
	@Mock
	UserDao userDao;
	
	@InjectMocks
	UserService userService;

	@Test
	public void TestThatUserIsReturned_WhenUserIsSearchedThatExists() {
		when(userDao.findByEmail("")).thenReturn(new User());
		assertNotNull(userService.loadUserByUsername(""));
	}
	
	@Test
	public void TestThatExceptionIsthrown_WhenUserIsSearchedThatDoesntExist() {
		when(userDao.findByEmail("")).thenReturn(null);
		Exception exception = assertThrows(UsernameNotFoundException.class, ()->{
			userService.loadUserByUsername("");
		});
		
		String expectedMessage = "User not found.";
		String actualMessage= exception.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
}
 