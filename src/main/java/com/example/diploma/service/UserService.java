package com.example.diploma.service;

import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.User;
import com.example.diploma.entity.UserEntity;

/**
 * @author anna
 */
public interface UserService {
    User add(RegisterReq req);

    User update(User user, String name);

    void delete(String name);

    User get(String name);

    UserEntity getEntity(String name);
}