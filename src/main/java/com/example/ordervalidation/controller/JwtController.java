
package com.example.ordervalidation.controller;

import com.example.ordervalidation.service.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/generate")
    public Map<String, String> generateToken(
            @RequestBody Map<String, Object> user) {

        String username = user.get("username").toString();

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.get("email"));
        claims.put("role", user.get("role"));

        String token = jwtService.generateToken(
                claims, username);

        return Map.of("token", token);
    }
}
