package com.eventhub.backend.usermanagement.signup.controller;

import com.eventhub.backend.usermanagement.signup.request.SignUpRequest;
import com.eventhub.backend.usermanagement.signup.response.SignUpResponse;
import com.eventhub.backend.usermanagement.signup.service.SignUpService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseSuccess<SignUpResponse> signup(@ModelAttribute SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }
}
