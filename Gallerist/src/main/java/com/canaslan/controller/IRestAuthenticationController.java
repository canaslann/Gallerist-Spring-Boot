package com.canaslan.controller;

import com.canaslan.dto.AuthRequest;
import com.canaslan.dto.AuthResponse;
import com.canaslan.dto.DtoUser;
import com.canaslan.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);
    
    public RootEntity<AuthResponse> authenticate(AuthRequest input);
    
    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);

}
