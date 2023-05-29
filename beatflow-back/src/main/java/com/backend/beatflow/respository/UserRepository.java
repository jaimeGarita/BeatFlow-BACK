package com.backend.beatflow.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.beatflow.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

    UserModel findByUserName(String username);
    
}
