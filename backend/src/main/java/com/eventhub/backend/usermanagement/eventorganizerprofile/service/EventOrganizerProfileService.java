package com.eventhub.backend.usermanagement.eventorganizerprofile.service;

import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;
import com.eventhub.backend.usermanagement.customerprofile.request.UpdateCustomerProfileRequest;
import com.eventhub.backend.usermanagement.eventorganizerprofile.request.UpdateEventOrganizerProfileRequest;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface EventOrganizerProfileService {
    public HttpResponseSuccess<EventOrganizerEntity> getEventOrganizerDetailsById(HttpServletRequest request, Integer userId);

    public HttpResponseSuccess<EventOrganizerEntity> updateEventOrganizerProfileDetails(UpdateEventOrganizerProfileRequest updateEventOrganizerProfileRequest, HttpServletRequest request);
}
