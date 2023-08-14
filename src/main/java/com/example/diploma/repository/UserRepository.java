package com.example.diploma.repository;

import com.example.diploma.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author anna
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * Возвращает Optional с объектом UserEntity, найденнным по логину в БД
     * @param email логин пользователя
     * @return Optional<UserEntity>
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Удаляет пользователя по логину
     * @param email логин пользователя
     */
    void deleteByEmail(String email);
}
