package com.example.diploma.service.impl;

import com.example.diploma.dto.Role;
import com.example.diploma.dto.User;
import com.example.diploma.entity.ImageEntity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.mapping.UserMapper;
import com.example.diploma.repository.UserRepository;
import com.example.diploma.service.ImageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ImageService imageService;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    private final String userName = "aaa@ug.ru";
    private final String password = "123456789";
    private final Role role = Role.USER;
    private final int id = 1;
    private final String fName = "Oleg";
    private final String lName = "Olegov";
    private final String phone = "+78008889922";
    private final String imagePath = "user/image/" + id;

    @Test
    void updateValidUserTest() {
        String newPhone = "+78005553535";
        String newLName = "Petrov";
        User newUser = new User(id, userName, fName, newLName, newPhone, imagePath);
        UserEntity entity = getEntity();
        UserEntity newEntity = new UserEntity(id, password, userName, fName, newLName,
                newPhone, role, new ImageEntity());
        when(repository.findByEmail(userName)).thenReturn(Optional.of(entity));
        when(mapper.userDtoToEntity(newUser, entity)).thenReturn(newEntity);
        when(mapper.entityToUserDto(newEntity)).thenReturn(newUser);
        when(repository.save(newEntity)).thenReturn(newEntity);
        User result = userService.update(newUser, userName);
        assertEquals(newLName, result.getLastName());
        assertEquals(newPhone, result.getPhone());
        verify(repository).save(newEntity);
    }

    @Test
    void updateInvalidUserTest() {
        User user = new User(id, userName, fName, lName, phone, imagePath);
        when(repository.findByEmail(userName)).thenReturn(Optional.empty());
        assertThrows(FindNoEntityException.class, () -> userService.update(user, userName));
    }

    @Test
    void deleteTest() {
        userService.delete(userName);
        verify(repository).deleteByEmail(userName);
    }

    @Test
    void getValidUsernameTest() {
        UserEntity entity = getEntity();
        User user = new User(id, userName, fName, lName, phone, imagePath);
        when(repository.findByEmail(userName)).thenReturn(Optional.of(entity));
        when(mapper.entityToUserDto(entity)).thenReturn(user);
        assertEquals(user, userService.get(userName));
    }

    @Test
    void getInvalidUsernameTest() {
        when(repository.findByEmail(userName)).thenReturn(Optional.empty());
        assertThrows(FindNoEntityException.class, () -> userService.get(userName));
    }

    @Test
    void uploadImageTest() throws IOException {
        UserEntity entity = getEntity();
        ImageEntity newImage = new ImageEntity(122);
        ImageEntity oldImage = entity.getImage();
        byte[] inputArray = "Test".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("fileName", inputArray);
        when(repository.findByEmail(userName)).thenReturn(Optional.of(entity));
        when(imageService.saveImage(mockMultipartFile)).thenReturn(newImage);
        userService.uploadImage(mockMultipartFile, userName);
        verify(imageService).saveImage(mockMultipartFile);
        verify(imageService).deleteImage(oldImage);
        entity.setImage(newImage);
        verify(repository).save(entity);
    }

    @Test
    void getEntityByValidIdTest() {
        UserEntity entity = getEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        assertEquals(entity, userService.getEntityById(id));
    }

    @Test
    void getEntityByInvalidIdTest() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(FindNoEntityException.class, () -> userService.getEntityById(id));
    }

    @Test
    void changePasswordTest() {
        when(repository.findByEmail(userName)).thenReturn(Optional.of(getEntity()));
        UserEntity entity = getEntity();
        String renewedPassword = "renewedPassword";
        entity.setPassword(renewedPassword);
        userService.changePassword(renewedPassword, userName);
        verify(repository).save(entity);
    }

    @Test
    void userExistsTrueTest() {
        when(repository.findByEmail(userName)).thenReturn(Optional.of(getEntity()));
        Assertions.assertTrue(userService.userExists(userName));
    }

    @Test
    void userExistsFalseTest() {
        when(repository.findByEmail(userName)).thenReturn(Optional.empty());
        Assertions.assertFalse(userService.userExists(userName));
    }


    @Test
    void createUserTest() {
        UserEntity entity = getEntity();
        userService.createUser(entity);
        verify(repository).save(entity);
    }

    private UserEntity getEntity() {
        return new UserEntity(id, password, userName, fName, lName, phone, role, new ImageEntity(23));
    }
}