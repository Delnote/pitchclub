package org.bot.controllers;

import org.bot.db.DataRepository;
import org.bot.entites.DataEntity;
import org.bot.token.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final DataRepository dataRepository;

    public AuthController(JwtUtil jwtUtil, DataRepository dataRepository) {
        this.jwtUtil = jwtUtil;
        this.dataRepository = dataRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Optional<DataEntity> dataEntity = dataRepository.findByEmail(username);
        if (dataEntity.isPresent() && dataEntity.get().getPassword().equals(password)) { // Dummy check
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
