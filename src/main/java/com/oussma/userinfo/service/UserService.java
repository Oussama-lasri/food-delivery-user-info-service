package com.oussma.userinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oussma.userinfo.dto.UserDTO;
import com.oussma.userinfo.entity.User;
import com.oussma.userinfo.mapper.UserMapper;
import com.oussma.userinfo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    
    private final UserRepository userRepo;
    private final UserMapper userMapper;


    public UserDTO addUser(UserDTO userDTO) {
       User savedUser = userRepo.save(userMapper.mapUserDTOToUser(userDTO));
       return userMapper.mapUserToUserDTO(savedUser);

    }

    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
         Optional<User> fetchedUser =  userRepo.findById(userId);
//         if(fetchedUser.isPresent())
//             return new ResponseEntity<>(userMaper.mapUserToUserDTO(fetchedUser.get()), HttpStatus.OK);
//         return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	return fetchedUser.map(user -> userMapper.mapUserToUserDTO(user))
    			.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
