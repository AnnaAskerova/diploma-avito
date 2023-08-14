package com.example.diploma.mapping;


import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.User;
import com.example.diploma.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * @author anna
 */
@Component
public class UserMapper {
    /**
     * Маппит {@link UserEntity} в dto
     * @param entity объект класса {@link UserEntity}
     * @return {@link User}
     */
    public User entityToUserDto(UserEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getFirstName(),
                entity.getLastName(), entity.getPhone(), entity.getImagePath());
    }

    /**
     * Частично заменяет поля объекта {@link UserEntity} полями из {@link User} и возвращает обновленный объект
     * @param user объект с новыми данными
     * @param entity объект с устаревшмими данными
     * @return обновленный объект {@link UserEntity}
     */
    public UserEntity userDtoToEntity(User user, UserEntity entity) {
        entity.setPhone(user.getPhone());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        return entity;
    }

    /**
     * Создает объект {@link UserEntity} из dto
     * @param req - объект класса {@link RegisterReq}
     * @return {@link UserEntity}
     */
    public UserEntity registerReqDtoToEntity(RegisterReq req) {
        return new UserEntity(req.getPassword(), req.getUsername(), req.getFirstName(),
                req.getLastName(), req.getPhone(), req.getRole());
    }
}
