package com.eventhub.backend.usermanagement.customerprofile.service;

import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.customerprofile.request.UpdateCustomerProfileRequest;
import com.eventhub.backend.usermanagement.login.request.LoginRequest;
import com.eventhub.backend.usermanagement.login.response.LoginResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public interface CustomerProfileService {
    HttpResponseSuccess<CustomerEntity> getCustomerDetails(HttpServletRequest request, Integer userId);
    public HttpResponseSuccess<CustomerEntity> updateCustomerProfileDetails(@ModelAttribute UpdateCustomerProfileRequest updateCustomerProfileRequest, HttpServletRequest request);
}
