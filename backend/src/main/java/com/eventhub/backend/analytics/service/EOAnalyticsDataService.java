package com.eventhub.backend.analytics.service;

import com.eventhub.backend.analytics.Entity.OverallEOAnalyticsDataEntity;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;


public interface EOAnalyticsDataService {
    HttpResponseSuccess<OverallEOAnalyticsDataEntity> getOverallEOAnalyticsData(Integer eventOrganizerId);

    HttpResponseSuccess<?> getEventSummariesByOrganizerId(Integer eventOrganizerId);
}
