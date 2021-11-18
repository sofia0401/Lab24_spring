package com.example.demo.DAO;


import com.example.demo.models.User;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDAO implements UserDetailsService {
    private UserRepository userRepo;

    @Autowired
    public UserDAO(UserRepository userRepo) {
        this.userRepo=userRepo;
    }
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = userRepo.findByLogin(login);
        return u;
    }
    public void create(String login,String password) {
        User u = new User();
        u.setLogin(login);
        u.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(u);
    }
    public List<User> readAll() {
        return userRepo.findAll();
    }
}



