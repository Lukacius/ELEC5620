package com.elec5620.portal.service;

import com.elec5620.portal.dto.UserDTO;
import com.elec5620.portal.model.User;
import com.elec5620.portal.repository.UserRepository;
import com.elec5620.portal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Package: com.elec5620.portal.service
 * Description:
 *
 * @Author Benjamin-Huang
 * @Create 2024/10/22 14:06
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 注册逻辑
    public String register(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email already in use.";
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // 加密密码
        user.setUserType(userDTO.getUserType());
        user.setDifficultyLevel(userDTO.getDifficultyLevel());

        userRepository.save(user);
        return "Registration successful";
    }

    // 登录逻辑并生成JWT
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return jwtUtil.generateToken(user.getEmail(), user.getUserType());
    }
}
