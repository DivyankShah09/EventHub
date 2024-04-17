package com.eventhub.backend.authentication.signup.controller;

import com.eventhub.backend.authentication.signup.request.SignUpRequest;
import com.eventhub.backend.authentication.signup.response.SignUpResponse;
import com.eventhub.backend.authentication.signup.service.SignUpService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @GetMapping()
    public HttpResponseSuccess<?> simpleGet() {
        return new HttpResponseSuccess<>(HttpStatus.OK.value(), "running", "api end point working");
    }

    @PostMapping("/signup")
    public HttpResponseSuccess<SignUpResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }
}
