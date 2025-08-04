package com.canaslan.service.impl;

import com.canaslan.dto.AuthRequest;
import com.canaslan.dto.AuthResponse;
import com.canaslan.dto.DtoUser;
import com.canaslan.dto.RefreshTokenRequest;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.jwt.JWTService;
import com.canaslan.model.RefreshToken;
import com.canaslan.model.User;
import com.canaslan.repository.RefreshTokenRepository;
import com.canaslan.repository.UserRepository;
import com.canaslan.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private AuthenticationProvider authenticationProvider;
    
    @Autowired
    private JWTService jwtService;
    
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private User createUser(AuthRequest authRequest) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(authRequest.getUsername());
        user.setPassword(encoder.encode(authRequest.getPassword()));

        return user;
    }
    
    private RefreshToken createRefreshToken(User user) {
    	RefreshToken refreshToken = new RefreshToken();
    	refreshToken.setCreateTime(new Date());
    	refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
    	refreshToken.setRefreshToken(UUID.randomUUID().toString());
    	refreshToken.setUser(user);
    	
    	return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest input) {
        DtoUser dtoUser = new DtoUser();
        User savedUser = userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }	

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
			authenticationProvider.authenticate(authenticationToken);
			
			Optional<User> optional = userRepository.findByUsername(input.getUsername());
			
			String accessToken = jwtService.generateToken(optional.get());
			RefreshToken refreshToken = createRefreshToken(optional.get());
			
			RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
			
			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.USERNAME_OR_PASSWORD_INVALID));
		}
	}
	
	public boolean isValidRefreshToken(Date expiredDate) {
		
		return new Date().before(expiredDate);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken());
		
		if (optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(refreshTokenRequest.getRefreshToken(), MessageType.NO_RECORD_EXIST));
		}
		
		if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(refreshTokenRequest.getRefreshToken(), MessageType.REFRESH_TOKEN_IS_EXPIRED));
		}
		
		User user = optRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}
}
