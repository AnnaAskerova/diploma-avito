package com.example.diploma.repository;

import com.example.diploma.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author anna
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    /**
     * Возвращает все комментарии к объявлению по его id
     * @param id id объявления
     * @return коллекция комментариев
     */
    List<CommentEntity> findAllByAd_Pk(int id);
}
