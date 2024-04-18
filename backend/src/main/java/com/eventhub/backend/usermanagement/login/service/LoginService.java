package com.eventhub.backend.authentication.login.service;

import com.eventhub.backend.authentication.login.request.LoginRequest;
import com.eventhub.backend.authentication.login.response.LoginResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;

public interface LoginService {
    HttpResponseSuccess<LoginResponse> login(LoginRequest loginRequest);
}
