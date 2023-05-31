package com.backend.beatflow.services.userService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.beatflow.model.UserModel;
import com.backend.beatflow.respository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public Optional<UserModel> getUserById(Long idUser){
        return userRepository.findById(idUser);
    }

    public UserModel getUserByUsername(String userName){
        return userRepository.findByUserName(userName);
    }
    
    public void save(UserModel user) {
        userRepository.save(user);
    }

}
