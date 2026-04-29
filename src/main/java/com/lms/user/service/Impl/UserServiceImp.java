package com.lms.user.service.Impl;

import com.lms.user.exceptions.UserNotFoundException;
import com.lms.user.model.User;
import com.lms.user.repo.UserRepository;
import com.lms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Override
    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User updateUser(Long id, String fullName) {
        User user = getById(id);
        user.setFullName(fullName);
        return repository.save(user);
    }

    @Override
    public void approveInstructor(Long id) {
        User user = getById(id);

        if (!"INSTRUCTOR".equals(user.getRole().getName())) {
            throw new RuntimeException("User is not an instructor");
        }

        user.setIsApproved(true);
        repository.save(user);
    }
}
