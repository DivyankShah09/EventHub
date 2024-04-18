package com.eventhub.backend.usermanagement.login.service;

import com.eventhub.backend.usermanagement.common.entity.UserEntity;
import com.eventhub.backend.usermanagement.common.repository.CustomerRepository;
import com.eventhub.backend.usermanagement.common.repository.EventOrganizerRepository;
import com.eventhub.backend.usermanagement.login.request.LoginRequest;
import com.eventhub.backend.usermanagement.login.response.LoginResponse;
import com.eventhub.backend.utils.enums.UserRole;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.eventhub.backend.utils.imageservice.S3CloudFront;
import com.eventhub.backend.utils.jwt.JWTAuthentication;
import com.eventhub.backend.utils.passwordencrcyption.PasswordEncypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Autowired
    JWTAuthentication jwtAuthentication;

    @Autowired
    S3CloudFront s3CloudFront;

    @Override
    public HttpResponseSuccess<LoginResponse> login(LoginRequest loginRequest) {

        Optional<? extends UserEntity> entityOptional = Optional.empty();
        String userType = loginRequest.getUserType();

        if (UserRole.user.toString().equals(userType.toLowerCase())) {
            entityOptional = customerRepository.findByEmail(loginRequest.getEmail());
        } else if (UserRole.event_organizer.toString().equals(userType.replace(" ", "_").toLowerCase())) {
            entityOptional = eventOrganizerRepository.findByEmail(loginRequest.getEmail());
        }

        if (entityOptional.isPresent()) {
            UserEntity entity = entityOptional.get();

            if (PasswordEncypter.validatePassword(loginRequest.getPassword(), entity.getPassword())) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setId(entity.getId());
                loginResponse.setName(entity.getName());
                loginResponse.setEmail(entity.getEmail());
                loginResponse.setUserType(userType);
                loginResponse.setJwtToken(jwtAuthentication.createToken(entity.getId(), entity.getName()));

                return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Login success", loginResponse);
            } else {
                return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Invalid user credentials", null);
            }
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Invalid user credentials", null);
        }
    }
}
