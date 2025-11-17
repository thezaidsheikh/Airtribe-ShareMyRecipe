package com.airtribe.sharemyrecipe.service;

import com.airtribe.sharemyrecipe.dto.AuthDTO;
import com.airtribe.sharemyrecipe.dto.UserDTO;
import com.airtribe.sharemyrecipe.entity.User;
import com.airtribe.sharemyrecipe.exception.InvalidCredentialsException;
import com.airtribe.sharemyrecipe.exception.UserNotActiveException;
import com.airtribe.sharemyrecipe.repository.UserRepository;
import com.airtribe.sharemyrecipe.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository _userRepository;

    @Autowired
    PasswordEncoder _passwordEncoder;

    public User registerUser(AuthDTO authDTO) {
        try {
            // Check if username already exists
            if (_userRepository.findByUsername(authDTO.getUsername()).isPresent()) {
                throw new RuntimeException("Username is already taken");
            }
            if (_userRepository.findByEmail(authDTO.getEmail()).isPresent()) {
                throw new RuntimeException("Username is already taken");
            }
            User user = new User();
            user.setEnabled(false);
            user.setUsername(authDTO.getUsername());
            user.setPassword(_passwordEncoder.encode(authDTO.getPassword()));
            user.setRole("CHEF");
            user.setEmail(authDTO.getEmail());
            return _userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error registering user: " + e.getMessage());
        }
    }

    public UserDTO loginUser(AuthDTO authDTO) throws InvalidCredentialsException, UserNotActiveException, Exception {
        User user = _userRepository.findByUsername(authDTO.getUsername()).get();
        if (user == null || !_passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        if (!user.getEnabled()) {
            throw new UserNotActiveException("User is not active");
        }
        UserDTO userDTO = new UserDTO(user);
        String token = TokenUtil.generateJwtToken(user);
        String refreshToken = TokenUtil.generateRefreshToken(user);
        userDTO.setToken(token);
        userDTO.setRefreshToken(refreshToken);
        return userDTO;
    }
}
