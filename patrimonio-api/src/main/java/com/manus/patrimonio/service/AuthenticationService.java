package com.manus.patrimonio.service;

import com.manus.patrimonio.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public Map<String, Object> authenticateUser(String username, String password) {
        try {
            // Carregar usuário do banco
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            // Verificar se a senha está correta
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new RuntimeException("Credenciais inválidas");
            }

            // Criar token de autenticação
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gerar JWT
            String jwt = tokenProvider.generateToken(authentication);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("username", username);
            response.put("roles", userDetails.getAuthorities().stream().map(Object::toString).toList());
            response.put("message", "Login realizado com sucesso");
            
            return response;
            
        } catch (Exception e) {
            throw new RuntimeException("Erro na autenticação: " + e.getMessage());
        }
    }
}
