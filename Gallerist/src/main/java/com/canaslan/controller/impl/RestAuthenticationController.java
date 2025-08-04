package com.canaslan.controller.impl;

import com.canaslan.controller.IRestAuthenticationController;
import com.canaslan.controller.RestBaseController;
import com.canaslan.controller.RootEntity;
import com.canaslan.dto.AuthRequest;
import com.canaslan.dto.AuthResponse;
import com.canaslan.dto.DtoUser;
import com.canaslan.dto.RefreshTokenRequest;
import com.canaslan.service.IAuthenticationService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationController extends RestBaseController implements IRestAuthenticationController {
	
	@Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.register(input));
    }
    
    @PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
		// TODO Auto-generated method stub
		return ok(authenticationService.authenticate(input));
	}
    
    @PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		// TODO Auto-generated method stub
		return ok(authenticationService.refreshToken(refreshTokenRequest));
	}
}
