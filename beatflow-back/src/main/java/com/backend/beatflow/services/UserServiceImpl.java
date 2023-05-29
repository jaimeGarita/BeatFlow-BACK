package com.backend.beatflow.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.beatflow.model.UserModel;
import com.backend.beatflow.respository.UserRepository;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public Optional<UserModel> getUserById(Long idUser){
        return userRepository.findById(idUser);
    }

}
