package com.eventhub.backend.booking.service;

import com.eventhub.backend.booking.entity.BookingEntity;
import com.eventhub.backend.booking.repository.BookingRepository;
import com.eventhub.backend.booking.request.BookingRequest;
import com.eventhub.backend.event.entity.EventEntity;
import com.eventhub.backend.event.repository.EventRepository;
import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.common.repository.CustomerRepository;
import com.eventhub.backend.utils.awsemailservice.EmailMessages;
import com.eventhub.backend.utils.awsemailservice.SendEmailToSubscriber;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.eventhub.backend.utils.jwt.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    JWTAuthentication jwtAuthentication;

    @Autowired
    EmailMessages emailMessages;
    @Autowired
    SendEmailToSubscriber sendEmailToSubscriber;

    @Override
    public HttpResponseSuccess<BookingEntity> saveBooking(BookingRequest bookingRequest, HttpServletRequest request) {

        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);

        if (userId != null) {
            Optional<EventEntity> event = eventRepository.findById(bookingRequest.getEventId());
            Optional<CustomerEntity> customerEntity = customerRepository.findById(userId);

            if (event.get().getDate().after(bookingRequest.getDate())) {

                System.out.println("booking request date: " + bookingRequest.getDate());
                BookingEntity bookingEntity = new BookingEntity();
                bookingEntity.setUser(customerEntity.get());
                bookingEntity.setEvent(event.get());
                bookingEntity.setDate(bookingRequest.getDate());
                bookingEntity.setQuantity(bookingRequest.getQuantity());
                bookingEntity.setTotalPrice(Integer.valueOf(event.get().getPrice()) * bookingRequest.getQuantity());
                System.out.println("booking entity date: " + bookingEntity.getDate());
                BookingEntity savedBookingEntity = bookingRepository.save(bookingEntity);


                String emailSubject = emailMessages.getBookingEmailSubject();

                String emailMessage = emailMessages.getBookingEmailMessage(customerEntity.get().getName(), event.get().getName(), savedBookingEntity);

//                sendEmailToSubscriber.sendEmailToSubsciber(emailSubject, emailMessage, customerEntity.get().getEmail());

                return new HttpResponseSuccess<BookingEntity>(HttpStatus.OK.value(), "Event booked successfully", bookingEntity);
            } else {
                return new HttpResponseSuccess<>(HttpStatus.BAD_REQUEST.value(), "Event booking closed",
                        null);
            }
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<BookingEntity> getBookingById(HttpServletRequest request, Integer bookingId) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);

        if (userId != null) {
            Optional<BookingEntity> bookingEntity = bookingRepository.findById(bookingId);
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    bookingEntity.get());
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<?> getBookingByUser(HttpServletRequest request) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        if (userId != null) {
            ArrayList<BookingEntity> bookingList = bookingRepository.findByUserId(userId);

            return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Event booked successfully", bookingList);
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<?> getBookingByEvent(Integer eventId, HttpServletRequest request) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        if (userId != null) {
            ArrayList<BookingEntity> bookingList = bookingRepository.findByEventId(eventId);

            return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Event booked successfully", bookingList);
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<?> deleteBookingById(Integer id, HttpServletRequest request) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        if (userId != null) {
            BookingEntity bookingEntity = bookingRepository.findById(id).get();
            bookingRepository.delete(bookingEntity);

            return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Booking deleted successfully", "booking deleted");
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }
}
