package com.oussma.userinfo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oussma.userinfo.dto.UserDTO;

import com.oussma.userinfo.service.UserService;

class UserControllerTest {

	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController ;
	
	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this); 
	    }
	@Test
	void TestAddUser() {
		
		UserDTO mockUser = new UserDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
		
		 when(userService.addUser(mockUser)).thenReturn(mockUser);
		 
		 ResponseEntity<UserDTO> response = userController.addUser(mockUser);
		 
	     assertEquals(HttpStatus.CREATED, response.getStatusCode());
	     assertEquals(mockUser, response.getBody());
	     
	     verify(userService, times(1)).addUser(mockUser);
	}
	
	@Test
	void TestFetchUserDetailsById() {
		UserDTO mockUser = new UserDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
		Integer id  = 1;
		
		 when(userService.fetchUserDetailsById(id)).thenReturn(new ResponseEntity<>(mockUser, HttpStatus.CREATED) );
		 
		 ResponseEntity<UserDTO> response = userController.fetchUserDetailsById(id);
		 
	     assertEquals(HttpStatus.CREATED, response.getStatusCode());
	     assertEquals(mockUser, response.getBody());
	     
	     verify(userService, times(1)).fetchUserDetailsById(id);
	}
		
	
}
