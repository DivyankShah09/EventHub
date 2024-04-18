package com.eventhub.backend.usermanagement.login.service;

import com.eventhub.backend.usermanagement.login.request.LoginRequest;
import com.eventhub.backend.usermanagement.login.response.LoginResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;

public interface LoginService {
    HttpResponseSuccess<LoginResponse> login(LoginRequest loginRequest);
}
