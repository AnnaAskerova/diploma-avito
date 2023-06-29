package com.example.diploma.service.impl;

import com.example.diploma.dto.UserSecurity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author anna
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDetailsManagerImpl implements UserDetailsService {

    private final UserRepository repository;

    public void changePassword(String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = getEntity(currentUser.getName());
        entity.setPassword(newPassword);
        repository.save(entity);
    }

    public boolean userExists(String username) {
        return repository.findByEmail(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserSecurity(getEntity(username));
    }

    public UserEntity getEntity(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> new FindNoEntityException("пользователь"));
    }

    public void createUser(UserEntity user) {
        log.info("Регистрация нового пользователя");
        repository.save(user);
    }
}
