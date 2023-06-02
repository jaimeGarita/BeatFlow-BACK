package com.backend.beatflow.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.beatflow.model.AccessTokenModel;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessTokenModel, Long>{


}
