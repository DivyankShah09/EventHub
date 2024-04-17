package com.eventhub.backend.authentication.signup.service;

import com.eventhub.backend.authentication.signup.request.SignUpRequest;
import com.eventhub.backend.authentication.signup.response.SignUpResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;

public interface SignUpService {
    HttpResponseSuccess<SignUpResponse> signUp(SignUpRequest signUpRequest);
}
