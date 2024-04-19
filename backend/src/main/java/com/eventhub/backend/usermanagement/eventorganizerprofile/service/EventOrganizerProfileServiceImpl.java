package com.eventhub.backend.usermanagement.eventorganizerprofile.service;

import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;
import com.eventhub.backend.usermanagement.common.repository.EventOrganizerRepository;
import com.eventhub.backend.usermanagement.customerprofile.request.UpdateCustomerProfileRequest;
import com.eventhub.backend.usermanagement.eventorganizerprofile.request.UpdateEventOrganizerProfileRequest;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.eventhub.backend.utils.imageservice.S3CloudFront;
import com.eventhub.backend.utils.jwt.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class EventOrganizerProfileServiceImpl implements EventOrganizerProfileService{
    @Autowired
    JWTAuthentication jwtAuthentication;
    @Autowired
    S3CloudFront s3CloudFront;
    @Autowired
    EventOrganizerRepository eventOrganizerRepository;
    @Override
    public HttpResponseSuccess<EventOrganizerEntity> getEventOrganizerDetailsById(HttpServletRequest request, Integer userId) {

        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer tokenUserId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);

        if(tokenUserId != null && tokenUserId == userId){
            Optional<EventOrganizerEntity> eventOrganizer = eventOrganizerRepository.findById(userId);
            return new HttpResponseSuccess<EventOrganizerEntity>(HttpStatus.OK.value(), "User details fetched successfully",
                    eventOrganizer.get());
        } else {
            return new HttpResponseSuccess<EventOrganizerEntity>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<EventOrganizerEntity> updateEventOrganizerProfileDetails(UpdateEventOrganizerProfileRequest updateEventOrganizerProfileRequest, HttpServletRequest request) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer tokenUserId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        System.out.println(tokenUserId);
        if(tokenUserId != null){
            EventOrganizerEntity eventOrganizerEntity = eventOrganizerRepository.findById(updateEventOrganizerProfileRequest.getId()).get();
            eventOrganizerEntity.setName(updateEventOrganizerProfileRequest.getName());
            eventOrganizerEntity.setMobileNumber(updateEventOrganizerProfileRequest.getMobileNumber());
            eventOrganizerEntity.setBusinessName(updateEventOrganizerProfileRequest.getBusinessName());


            String filePath = "event-organizer/"+updateEventOrganizerProfileRequest.getEmail()+ "/";
            if(updateEventOrganizerProfileRequest.getProfilePicture() != null) {
                System.out.println("image: "+updateEventOrganizerProfileRequest.getProfilePicture());
                String imageUrl = s3CloudFront.uploadImageGetUrl(filePath, updateEventOrganizerProfileRequest.getProfilePicture());
                eventOrganizerEntity.setProfilePictureUrl(imageUrl);
            }
            eventOrganizerRepository.save(eventOrganizerEntity);

            return new HttpResponseSuccess<EventOrganizerEntity>(HttpStatus.OK.value(), "User details updated successfully",
                    eventOrganizerEntity);
        } else {
            return new HttpResponseSuccess<EventOrganizerEntity>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }
}
