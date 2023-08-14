package com.example.diploma.mapping;

import com.example.diploma.dto.Comment;
import com.example.diploma.dto.CreateComment;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.entity.CommentEntity;
import com.example.diploma.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author anna
 */
@Component
public class CommentMapper {
    /**
     * Маппит {@link CommentEntity} в dto
     * @param entity объект класса {@link CommentEntity}
     * @return {@link CommentEntity}
     */
    public Comment entityToCommentDto(CommentEntity entity) {
        return new Comment(entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), getMillis(entity.getCreatedAt()),
                entity.getPk(), entity.getText());
    }

    /**
     * Собирает {@link CommentEntity} из входящих ранных
     * @param createComment содержит данные комментария
     * @param ad содержит данные объявления
     * @param author содержит данные автора
     * @return {@link CommentEntity}
     */
    public CommentEntity createCommentToEntity(CreateComment createComment, AdEntity ad, UserEntity author) {
        return new CommentEntity(author, LocalDateTime.now(), createComment.getText(), ad);
    }

    /**
     * Возвращает количество милисекунд, прошедших с 01.01.1970 по переданное в параметрах время
     * @param time время
     * @return количество милисекунд
     */
    private long getMillis(LocalDateTime time) {
        return time.toInstant(ZoneOffset.ofHours(5)).toEpochMilli();
    }

}
