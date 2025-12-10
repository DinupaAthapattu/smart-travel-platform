package com.smarttravel.user_service.service;

import com.smarttravel.user_service.dto.UserRequestDto;
import com.smarttravel.user_service.dto.UserResponseDto;
import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto requestDto);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();
}
