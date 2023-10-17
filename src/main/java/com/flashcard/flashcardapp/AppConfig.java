package com.flashcard.flashcardapp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class AppConfig {
    @Bean
    UserDetailsManager users(DataSource dataSource) {
        var users = new JdbcUserDetailsManager(dataSource);
        return users;
    }
}
