package com.eventhub.backend.event.controller;

import com.eventhub.backend.event.entity.EventEntity;
import com.eventhub.backend.event.request.EventRequest;
import com.eventhub.backend.event.service.EventService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping()
    public HttpResponseSuccess<?> getAllEvents(HttpServletRequest request) {
        return eventService.getAllEvents(request);
    }

    @GetMapping(params = {"id"})
    public HttpResponseSuccess<?> getEventByEventId(HttpServletRequest request, @RequestParam(name = "id") Integer eventId) {
        return eventService.getEventByEventId(request, eventId);
    }

    @GetMapping("/my-events")
    public HttpResponseSuccess<?> getEventByEventOrganizer(HttpServletRequest request) {
        return eventService.getEventByEventOrganizer(request);
    }

    @PostMapping(value = "/save-event", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseSuccess<EventEntity> saveEvent(@ModelAttribute EventRequest eventRequest, HttpServletRequest request) {
        return eventService.saveEvent(eventRequest, request);
    }
}
