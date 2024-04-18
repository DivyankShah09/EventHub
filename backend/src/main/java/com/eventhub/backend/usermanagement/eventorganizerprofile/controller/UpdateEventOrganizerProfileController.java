package com.eventhub.backend.usermanagement.eventorganizerprofile.controller;

import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;
import com.eventhub.backend.usermanagement.common.repository.EventOrganizerRepository;
import com.eventhub.backend.usermanagement.customerprofile.request.UpdateCustomerProfileRequest;
import com.eventhub.backend.usermanagement.customerprofile.service.CustomerProfileService;
import com.eventhub.backend.usermanagement.eventorganizerprofile.request.UpdateEventOrganizerProfileRequest;
import com.eventhub.backend.usermanagement.eventorganizerprofile.service.EventOrganizerProfileService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/event-organizer-profile")
public class UpdateEventOrganizerProfileController {
    @Autowired
    EventOrganizerProfileService eventOrganizerProfileService;

    @GetMapping(params = {"id"})
    public HttpResponseSuccess<EventOrganizerEntity> getEventOrganizerDetailsById(HttpServletRequest request, @RequestParam(name = "id") Integer userId) {
        return eventOrganizerProfileService.getEventOrganizerDetailsById(request, userId);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseSuccess<EventOrganizerEntity> updateEventOrganizerProfileDetails(@ModelAttribute UpdateEventOrganizerProfileRequest updateEventOrganizerProfileRequest, HttpServletRequest request) {
        return eventOrganizerProfileService.updateEventOrganizerProfileDetails(updateEventOrganizerProfileRequest, request);
    }
}
