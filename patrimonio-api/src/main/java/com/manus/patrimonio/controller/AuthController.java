package com.manus.patrimonio.controller;

import com.manus.patrimonio.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password) {
        try {
            if (username == null || password == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "erro", "Dados inválidos", "mensagem", "Username e password são obrigatórios"));
            }
            
            Map<String, Object> response = authenticationService.authenticateUser(username, password);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("status", 400, "erro", "Erro de autenticação", "mensagem", e.getMessage()));
        }
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> loginGet(@RequestParam String username, @RequestParam String password) {
        return login(username, password);
    }
}
