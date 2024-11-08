package com.elec5620.portal.controller;

import com.elec5620.portal.dto.UserDTO;
import com.elec5620.portal.model.DifficultyLevel;
import com.elec5620.portal.model.User;
import com.elec5620.portal.repository.UserRepository;
import com.elec5620.portal.service.UserService;
import com.elec5620.portal.util.JwtUtil;
import org.json.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * ClassName: UserController
 * Package: com.elec5620.portal.controller
 * Description:
 *
 * @Author Benjamin-Huang
 * @Create 2024/10/22 14:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        String message = userService.register(userDTO);
        return ResponseEntity.ok("{\"message\": \"" + message + "\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        String token = userService.login(email, password);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }

        User user = (User)userRepository.findByEmail(email).orElse(null);

        // 解析Token以获取Token类型
        Claims claims = new JwtUtil().validateToken(token);
        String tokenType = claims.get("tokenType", String.class);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "name", user.getName(),
                "tokenType", tokenType,
                "message", "Login successful"
        ));
    }

    @PostMapping("/update/{email}")
    public ResponseEntity<String> updateUserInfoByEmail(@PathVariable String email, @RequestBody String userPrompt) {
        JSONObject jsonObject = new JSONObject(userPrompt);
        String prompt = jsonObject.getString("prompt");
        return ResponseEntity.ok(userService.updateUserInfoByEmail(email,prompt));
    }

}
