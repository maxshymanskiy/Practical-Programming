package edu.java.lab4.service;

import edu.java.lab4.dto.request.LoginDto;
import edu.java.lab4.dto.request.RegisterDto;
import edu.java.lab4.dto.response.AuthDto;

public interface AuthService {

    AuthDto login(LoginDto request);

    AuthDto register(RegisterDto request);
}

