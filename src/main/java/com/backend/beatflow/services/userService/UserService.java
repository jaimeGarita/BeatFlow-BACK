package com.backend.beatflow.services.userService;

import java.util.List;
import java.util.Optional;

import com.backend.beatflow.model.UserModel;

public interface UserService {
    public Optional<UserModel> getUserById(Long idUser);
    public List<UserModel> getAll();
}
