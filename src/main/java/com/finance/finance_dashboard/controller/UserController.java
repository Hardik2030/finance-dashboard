package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.dto.UserRequestDto;
import com.finance.finance_dashboard.model.Role;
import com.finance.finance_dashboard.model.User;
import com.finance.finance_dashboard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody UserRequestDto dto) {

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();

        return userService.createUser(user);
    }
    @GetMapping("/check-role")
    public String checkRole(Authentication authentication) {
        return authentication.getAuthorities().toString();
    }
}
