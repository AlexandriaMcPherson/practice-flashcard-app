package com.flashcard.flashcardapp.services;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flashcard.flashcardapp.domain.models.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtEncoder encoder;

    private final UserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public String issueToken(Authentication authentication) {
        Instant now = Instant.now();

        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            // トークンの有効期限を1時間とする
            .expiresAt(now.plusSeconds(36000L))
            .subject(authentication.getName())
            .build();
        // @formatter:on

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }
    
    public void addUser(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(passwordEncoder.encode(user.getPassword()))
            .roles("USER")
            .build();
        userDetailsManager.createUser(userDetails);
    }

}
