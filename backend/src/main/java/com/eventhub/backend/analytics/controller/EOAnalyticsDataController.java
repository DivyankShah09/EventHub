package com.eventhub.backend.analytics.controller;

import com.eventhub.backend.analytics.Entity.OverallEOAnalyticsDataEntity;
import com.eventhub.backend.analytics.service.EOAnalyticsDataService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class EOAnalyticsDataController {
    @Autowired
    EOAnalyticsDataService EOAnalyticsDataService;

    @GetMapping(params = "event-organizer-id")
    public HttpResponseSuccess<OverallEOAnalyticsDataEntity> getOverallEOAnalyticsData(@RequestParam(name = "event-organizer-id") Integer eventOrganizerId) {
        return EOAnalyticsDataService.getOverallEOAnalyticsData(eventOrganizerId);
    }

    //    @CrossOrigin(origins = "*")
    @GetMapping(value = "/summaries", params = "event-organizer-id")
    public HttpResponseSuccess<?> getEventSummariesByOrganizerId(@RequestParam(name = "event-organizer-id") Integer eventOrganizerId) {
        return EOAnalyticsDataService.getEventSummariesByOrganizerId(eventOrganizerId);
    }
}
