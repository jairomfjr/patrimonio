package com.manus.patrimonio.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123";
        String encodedPassword = encoder.encode(password);
        
        System.out.println("Senha original: " + password);
        System.out.println("Senha hash: " + encodedPassword);
        System.out.println("Verificação: " + encoder.matches(password, encodedPassword));
        
        // Gerar hash para outras senhas comuns
        System.out.println("\nOutras senhas:");
        System.out.println("user123: " + encoder.encode("user123"));
        System.out.println("123456: " + encoder.encode("123456"));
    }
}
