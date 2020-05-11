package it.polito.ezgas.converter;

import java.util.ArrayList;
import java.util.List;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {
	public static User toEntity(UserDto userDto) {
		User user;
		user = new User(userDto.getUserName(),userDto.getPassword(),userDto.getEmail(),userDto.getReputation());
		user.setUserId(userDto.getUserId());
		user.setAdmin(userDto.getAdmin());
		return user;
	}
	
	public static List<UserDto> toDto(List<User> userList) {
		List<UserDto> userDtoList = new ArrayList<UserDto>(); 
		for (User user : userList) {
			userDtoList.add(toDto(user));
		}
		return userDtoList;
	}
	
	public static UserDto toDto(User user) {
		UserDto userDto; 
		userDto = new UserDto(user.getUserId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getReputation(),user.getAdmin());
		return userDto;
	}
	
}
