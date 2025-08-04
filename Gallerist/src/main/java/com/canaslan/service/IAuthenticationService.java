package com.canaslan.service;

import com.canaslan.dto.AuthRequest;
import com.canaslan.dto.AuthResponse;
import com.canaslan.dto.DtoUser;
import com.canaslan.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);
    
    public AuthResponse authenticate(AuthRequest input);
    
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
