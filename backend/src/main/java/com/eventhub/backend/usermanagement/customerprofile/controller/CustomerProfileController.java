package com.eventhub.backend.usermanagement.customerprofile.controller;

import com.eventhub.backend.event.entity.EventEntity;
import com.eventhub.backend.event.request.EventRequest;
import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.customerprofile.request.UpdateCustomerProfileRequest;
import com.eventhub.backend.usermanagement.customerprofile.service.CustomerProfileService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/customer-profile")
public class CustomerProfileController {
    @Autowired
    CustomerProfileService customerProfileService;

    @GetMapping(params = {"id"})
    public HttpResponseSuccess<CustomerEntity> getCustomerDetailsById(HttpServletRequest request, @RequestParam(name = "id") Integer userId) {
        return customerProfileService.getCustomerDetails(request, userId);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseSuccess<CustomerEntity> updateCustomerProfileDetails(@ModelAttribute UpdateCustomerProfileRequest updateCustomerProfileRequest, HttpServletRequest request) {
        return customerProfileService.updateCustomerProfileDetails(updateCustomerProfileRequest, request);
    }
}
