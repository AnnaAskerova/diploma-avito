package com.example.diploma.repository;
import com.example.diploma.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author anna
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    /**
     * Возвращает все объявления автора по его логину
     * @param email логин автора
     * @return коллекция объявлений автора
     */
    List<AdEntity> findAllByAuthorEmail(String email);
}
