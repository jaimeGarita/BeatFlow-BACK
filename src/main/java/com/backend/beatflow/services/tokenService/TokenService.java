package com.backend.beatflow.services.tokenService;

import com.backend.beatflow.model.AccessTokenModel;

public interface TokenService {
    void saveToken(AccessTokenModel accessToken);
}
