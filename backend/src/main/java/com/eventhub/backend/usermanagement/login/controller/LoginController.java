package com.eventhub.backend.usermanagement.login.controller;

import com.eventhub.backend.usermanagement.login.request.LoginRequest;
import com.eventhub.backend.usermanagement.login.response.LoginResponse;
import com.eventhub.backend.usermanagement.login.service.LoginService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    public HttpResponseSuccess<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}
