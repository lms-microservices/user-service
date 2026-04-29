package com.lms.user.controller;

import com.lms.user.dto.*;
import com.lms.user.mapper.UserMapper;
import com.lms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return UserMapper.toResponse(service.getById(id));
    }

    @GetMapping("/email/{email}")
    public UserResponse getByEmail(@PathVariable String email) {
        return UserMapper.toResponse(service.getByEmail(email));
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        return UserMapper.toResponse(
                service.updateUser(id, request.getFullName())
        );
    }

    @PutMapping("/{id}/approve-instructor")
    public void approveInstructor(@PathVariable Long id) {
        service.approveInstructor(id);
    }
}