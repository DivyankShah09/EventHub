package com.eventhub.backend.usermanagement.signup.service;

import com.eventhub.backend.usermanagement.signup.request.SignUpRequest;
import com.eventhub.backend.usermanagement.signup.response.SignUpResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;

public interface SignUpService {
    HttpResponseSuccess<SignUpResponse> signUp(SignUpRequest signUpRequest);
}
