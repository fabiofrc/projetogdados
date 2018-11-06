package com.gdados.projeto.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String getPasswordEncoder(String plainPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Senha sem cripto: " + plainPassword);
        String hashedPassword = passwordEncoder.encode(plainPassword);

        System.out.println("Senha encriptada usando hash BCrypt: " + hashedPassword);
        return hashedPassword;
    }

    public String encodePasswordWithBCrypt(String plainPassword) {
        //return new BCryptPasswordEncoder().encode(plainPassword);
        return passwordEncoder.encode(plainPassword);
    }

}
