package com.eventhub.backend.booking.service;

import com.eventhub.backend.booking.entity.BookingEntity;
import com.eventhub.backend.booking.request.BookingRequest;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;

import javax.servlet.http.HttpServletRequest;

public interface BookingService {
    HttpResponseSuccess<BookingEntity> saveBooking(BookingRequest bookingRequest, HttpServletRequest request);

    HttpResponseSuccess<BookingEntity> getBookingById(HttpServletRequest request, Integer bookingId);

    HttpResponseSuccess<?> getBookingByUser(HttpServletRequest request);

    HttpResponseSuccess<?> getBookingByEvent(Integer eventId, HttpServletRequest request);

    HttpResponseSuccess<?> deleteBookingById(Integer id, HttpServletRequest request);
}
