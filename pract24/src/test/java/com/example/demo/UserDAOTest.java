package com.example.demo;

import com.example.demo.DAO.UserDAO;
import com.example.demo.models.User;
import com.example.demo.repos.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserDAOTest {
    @Mock
    private UserRepository userRepo;
    @Captor
    ArgumentCaptor<User> captor;
    @Test
    void getUsers() {
        User user1 = new User();
        user1.setLogin("Log1");
        User user2 = new User();
        user2.setLogin("Log2");
        Mockito.when(userRepo.findAll()).thenReturn(List.of(user1,user2));
        UserDAO userDAO = new UserDAO(userRepo);

        Assertions.assertEquals(2,userDAO.readAll().size());

    }
    @Test
    void findUser() {
        User user = new User();
        user.setLogin("UserName");
        UserDAO userDAO = new UserDAO(userRepo);

        Mockito.when(userRepo.findByLogin("UserName")).thenReturn(user);

        Assertions.assertEquals("UserName",userDAO.loadUserByUsername("UserName").getUsername());


    }
}
