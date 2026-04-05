package com.finance.finance_dashboard.service;
import com.finance.finance_dashboard.model.User;
import com.finance.finance_dashboard.model.UserStatus;
import com.finance.finance_dashboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User createUser(User user)
    {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
        {
            throw new RuntimeException("Email Already exists");
        }
        user.setStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }
}
