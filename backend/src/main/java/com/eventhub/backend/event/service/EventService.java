package com.eventhub.backend.event.service;

import com.eventhub.backend.event.entity.EventEntity;
import com.eventhub.backend.event.request.EventRequest;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;

import javax.servlet.http.HttpServletRequest;

public interface EventService {
    HttpResponseSuccess<?> getAllEvents(HttpServletRequest request);

    HttpResponseSuccess<?> getEventByEventId(HttpServletRequest request, Integer eventId);

    HttpResponseSuccess<?> getEventByEventOrganizer(HttpServletRequest request);

    HttpResponseSuccess<EventEntity> saveEvent(EventRequest eventRequest, HttpServletRequest request);
}
