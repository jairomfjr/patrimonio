package com.manus.patrimonio.controller;

import com.manus.patrimonio.service.AuthenticationService;
import com.manus.patrimonio.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
    
    @GetMapping("/auth-service")
    public String testAuthService() {
        try {
            if (authenticationService != null) {
                return "AuthenticationService está disponível";
            } else {
                return "AuthenticationService é null";
            }
        } catch (Exception e) {
            return "Erro ao acessar AuthenticationService: " + e.getMessage();
        }
    }
    
    @GetMapping("/user-details")
    public String testUserDetails(@RequestParam String username) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return "Usuário encontrado: " + userDetails.getUsername() + " com roles: " + userDetails.getAuthorities();
        } catch (Exception e) {
            return "Erro ao carregar usuário: " + e.getMessage();
        }
    }

    @GetMapping("/test-auth")
    public String testAuthenticationService() {
        try {
            if (authenticationService != null) {
                // Testar se consegue carregar o usuário admin
                return "AuthenticationService está disponível";
            } else {
                return "AuthenticationService é null";
            }
        } catch (Exception e) {
            return "Erro ao acessar AuthenticationService: " + e.getMessage();
        }
    }
    
    @PostMapping("/test-login")
    public String testLogin(@RequestParam String username, @RequestParam String password) {
        try {
            Map<String, Object> result = authenticationService.authenticateUser(username, password);
            return "Login bem-sucedido: " + result.toString();
        } catch (Exception e) {
            return "Erro no login: " + e.getMessage();
        }
    }
}
