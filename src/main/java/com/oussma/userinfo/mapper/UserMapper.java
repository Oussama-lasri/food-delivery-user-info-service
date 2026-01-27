package com.oussma.userinfo.mapper;

import org.mapstruct.Mapper;

import com.oussma.userinfo.dto.UserDTO;
import com.oussma.userinfo.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
	 	User mapUserDTOToUser(UserDTO userDTO);
	    UserDTO mapUserToUserDTO(User user);
}
