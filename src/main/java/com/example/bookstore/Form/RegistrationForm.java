package com.example.bookstore.Form;

import com.example.bookstore.Data.Users;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String nickname;
    private String password;
    private String email;
    public Users toUser(PasswordEncoder passwordEncoder) {
        return new Users(username, nickname, passwordEncoder.encode(password), email);
    }
}
