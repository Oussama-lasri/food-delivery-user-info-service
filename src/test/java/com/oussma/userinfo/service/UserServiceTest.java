package com.oussma.userinfo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oussma.userinfo.dto.UserDTO;
import com.oussma.userinfo.entity.User;
import com.oussma.userinfo.mapper.UserMapper;
import com.oussma.userinfo.repository.UserRepository;

class UserServiceTest {
		
		@Mock
	 	UserRepository userRepo;
		@Mock
	    UserMapper userMapper;
		
		@InjectMocks
		UserService userService;
	
		
	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this); 
	    }
	 @Test
	 void  addUser_Success() {
		 
		 	
		 UserDTO mockUserDTO = new UserDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
		User mockUser = new User(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
		// Arrange
		when(userMapper.mapUserDTOToUser(mockUserDTO)).thenReturn(mockUser);
		when(userMapper.mapUserToUserDTO(mockUser)).thenReturn(mockUserDTO);
		when(userRepo.save(mockUser)).thenReturn(mockUser);
		 
		// act
		 UserDTO userDTO = userService.addUser(mockUserDTO);
		 
		 // Assert
	     assertEquals(mockUserDTO, userDTO);
	   
	     
	     verify(userRepo, times(1)).save(mockUser);
	     verify(userMapper, times(1)).mapUserDTOToUser(mockUserDTO);
	     verify(userMapper, times(1)).mapUserToUserDTO(mockUser);
	 }
	 
	 @Test
	 void addUser_shouldThrowException_whenRepoFails() {

	     UserDTO dto = new UserDTO(1, "R1", "Addr", "City", "Desc");
	     User user = new User(1, "R1", "Addr", "City", "Desc");

	     when(userMapper.mapUserDTOToUser(dto)).thenReturn(user);
	     when(userRepo.save(user)).thenThrow(new RuntimeException("DB error"));

	     // Act + Assert
	     RuntimeException ex = assertThrows(RuntimeException.class, () -> {
	         userService.addUser(dto);
	     });

	     assertEquals("DB error", ex.getMessage());
	 }
	 
	 @Test
	 void fetchUserDetailsById_shouldReturnUserDTO_whenUserExists() {

	     Integer userId = 1;

	     User user = new User();
	     user.setUserId(userId);

	     UserDTO userDTO = new UserDTO();
	     userDTO.setUserId(userId);

	     when(userRepo.findById(userId)).thenReturn(Optional.of(user));
	     when(userMapper.mapUserToUserDTO(user)).thenReturn(userDTO);

	     ResponseEntity<UserDTO> response = userService.fetchUserDetailsById(userId);

	     assertEquals(HttpStatus.OK, response.getStatusCode());
	     assertNotNull(response.getBody());
	     assertEquals(userId, response.getBody().getUserId());
	 }

}
