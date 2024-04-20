package com.eventhub.backend.booking.controller;

import com.eventhub.backend.booking.entity.BookingEntity;
import com.eventhub.backend.booking.request.BookingRequest;
import com.eventhub.backend.booking.service.BookingService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;


    @PostMapping("/save-booking")
    public HttpResponseSuccess<BookingEntity> saveBooking(@RequestBody BookingRequest bookingRequest, HttpServletRequest request) {
        return bookingService.saveBooking(bookingRequest, request);
    }

    @GetMapping()
    public HttpResponseSuccess<?> getBookingById(HttpServletRequest request) {
        return bookingService.getBookingByUser(request);
    }

    @GetMapping("/user-id")
    public HttpResponseSuccess<?> getBookingByUser(HttpServletRequest request) {
        return bookingService.getBookingByUser(request);
    }

    @GetMapping(params = {"event-id"})
    public HttpResponseSuccess<?> getBookingByEvent(@RequestParam(name = "event-id") Integer eventId, HttpServletRequest request) {
        return bookingService.getBookingByEvent(eventId, request);
    }

    @DeleteMapping(params = {"id"})
    public HttpResponseSuccess<?> deleteBookingById(@RequestParam(name = "id") Integer id, HttpServletRequest request) {
        return bookingService.deleteBookingById(id, request);
    }

}
