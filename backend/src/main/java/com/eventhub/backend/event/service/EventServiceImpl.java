package com.eventhub.backend.event.service;

import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;
import com.eventhub.backend.usermanagement.common.repository.CustomerRepository;
import com.eventhub.backend.usermanagement.common.repository.EventOrganizerRepository;
import com.eventhub.backend.event.entity.EventEntity;
import com.eventhub.backend.event.repository.EventRepository;
import com.eventhub.backend.event.request.EventRequest;
import com.eventhub.backend.utils.awsemailservice.EmailMessages;
import com.eventhub.backend.utils.awsemailservice.SendEmailToSubscriber;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.eventhub.backend.utils.imageservice.S3CloudFront;
import com.eventhub.backend.utils.jwt.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JWTAuthentication jwtAuthentication;

    @Autowired
    SendEmailToSubscriber sendEmailToSubscriber;

    @Autowired
    EmailMessages emailMessages;

    @Autowired
    S3CloudFront s3CloudFront;

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Override
    public HttpResponseSuccess<?> getAllEvents() {

            ArrayList<EventEntity> eventList = eventRepository.findAll();
            return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Events loaded successfully", eventList);

    }

    @Override
    public HttpResponseSuccess<?> getEventByEventId(HttpServletRequest request, Integer eventId) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        if (userId != null) {
            Optional<EventEntity> event = eventRepository.findById(eventId);
            return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Event loaded successfully", event.get());
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<?> getEventByEventOrganizer(HttpServletRequest request) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        if (userId != null) {
            ArrayList<EventEntity> eventList = eventRepository.findByEventOrganizerId(userId);
            return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Events loaded successfully", eventList);
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<EventEntity> saveEvent(EventRequest eventRequest, HttpServletRequest request) {
        System.out.println(eventRequest);
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);

        Optional<EventOrganizerEntity> eventOrganizer = eventOrganizerRepository.findById(userId);
        System.out.println(eventRequest.getId());
        if (userId != null) {
            EventEntity event;
            if(eventRequest.getId() != null){
                event = eventRepository.findById(eventRequest.getId()).get();
            }else{
                event = new EventEntity();
            }
            event.setEventOrganizer(eventOrganizer.get());
            event.setName(eventRequest.getName());
            event.setDescription(eventRequest.getDescription());
            event.setDate(eventRequest.getDate());
            event.setTime(convertStringToTime(eventRequest.getTime()));
            event.setLocation(eventRequest.getLocation());
            event.setPrice(eventRequest.getPrice());
            if(eventRequest.getImage() != null){
                String filePath = "event/"+eventOrganizer.get().getId()+"_"+eventOrganizer.get().getBusinessName()+"_"+eventRequest.getName().replace(" ", "_") + "/";
                String imageUrl = s3CloudFront.uploadImageGetUrl(filePath, eventRequest.getImage());
                event.setImageUrl(imageUrl);
            }
            eventRepository.save(event);

            ArrayList<CustomerEntity> customerEntityArrayList = customerRepository.findByCity(event.getLocation());
            for (CustomerEntity customerEntity : customerEntityArrayList) {
                String emailSubject = emailMessages.getNewEventEmailSubject(event.getLocation());
                String emailMessage = emailMessages.getNewEventEmailMessage(customerEntity.getName(), event.getLocation(), event);

//                sendEmailToSubscriber.sendEmailToSubsciber(emailSubject, emailMessage, customerEntity.getEmail());
            }


            return new HttpResponseSuccess<EventEntity>(HttpStatus.OK.value(), "Event added successfully", event);
        } else {
            return new HttpResponseSuccess<EventEntity>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }
    private static Time convertStringToTime(String timeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            java.util.Date parsedTime = sdf.parse(timeString);
            return new Time(parsedTime.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
