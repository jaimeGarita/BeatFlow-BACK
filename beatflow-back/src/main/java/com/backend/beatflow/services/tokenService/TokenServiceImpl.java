package com.backend.beatflow.services.tokenService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.beatflow.model.AccessTokenModel;
import com.backend.beatflow.respository.AccessTokenRepository;

@Service
public class TokenServiceImpl {
    
    @Autowired
    AccessTokenRepository accessTokenRepository;

    public void saveToken(Long id,  String accessToken, Date expirationDate){
        AccessTokenModel tokenEntity = new AccessTokenModel();
        tokenEntity.setUserId(id);
        tokenEntity.setToken(accessToken);
        tokenEntity.setExpirationDate(expirationDate);

        accessTokenRepository.save(tokenEntity);
    }

}
