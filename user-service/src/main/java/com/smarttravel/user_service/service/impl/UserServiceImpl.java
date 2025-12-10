package com.smarttravel.user_service.service.impl;

import com.smarttravel.user_service.dto.UserRequestDto;
import com.smarttravel.user_service.dto.UserResponseDto;
import com.smarttravel.user_service.entity.User;
import com.smarttravel.user_service.exception.UserNotFoundException;
import com.smarttravel.user_service.repository.UserRepository;
import com.smarttravel.user_service.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        User saved = userRepository.save(user);
        return new UserResponseDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }
}
