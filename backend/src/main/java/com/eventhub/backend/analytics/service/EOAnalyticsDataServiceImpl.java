package com.eventhub.backend.analytics.service;

import com.eventhub.backend.analytics.Entity.EventSummaryDataEntity;
import com.eventhub.backend.analytics.Entity.OverallEOAnalyticsDataEntity;
import com.eventhub.backend.analytics.repository.EODataRepository;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EOAnalyticsDataServiceImpl implements EOAnalyticsDataService {
    @Autowired
    EODataRepository EODataRepository;

    @Override
    public HttpResponseSuccess<OverallEOAnalyticsDataEntity> getOverallEOAnalyticsData(Integer eventOrganizerId) {
        String data = EODataRepository.getOverallEOAnalyticsData(eventOrganizerId);
        OverallEOAnalyticsDataEntity overallEOAnalyticsDataEntity = new OverallEOAnalyticsDataEntity();
        overallEOAnalyticsDataEntity.setTotalEvents(Long.valueOf(data.split(",")[0]));
        overallEOAnalyticsDataEntity.setTotalTickets(Long.valueOf(data.split(",")[1]));
        overallEOAnalyticsDataEntity.setTotalRevenue(Long.valueOf(data.split(",")[2]));

        return new HttpResponseSuccess<OverallEOAnalyticsDataEntity>(HttpStatus.OK.value(), "got details",
                overallEOAnalyticsDataEntity);
    }

    @Override
    public HttpResponseSuccess<ArrayList<EventSummaryDataEntity>> getEventSummariesByOrganizerId(Integer eventOrganizerId) {
        List<String> data = EODataRepository.getEventSummariesByOrganizerId(eventOrganizerId);

        ArrayList<EventSummaryDataEntity> eventSummaryDataEntityArrayList = new ArrayList<>();

        for (String d : data) {
            System.out.println(d);
            EventSummaryDataEntity eventSummaryDataEntity = new EventSummaryDataEntity();
            eventSummaryDataEntity.setEventId(Integer.valueOf(d.split(",")[0]));
            eventSummaryDataEntity.setEventName(d.split(",")[1]);
            eventSummaryDataEntity.setEventTotalRevenue(Long.valueOf(d.split(",")[2]));
            eventSummaryDataEntity.setEventTotalTickets(Long.valueOf(d.split(",")[3]));

            eventSummaryDataEntityArrayList.add(eventSummaryDataEntity);
        }
        return new HttpResponseSuccess<ArrayList<EventSummaryDataEntity>>(HttpStatus.OK.value(), "got details",
                eventSummaryDataEntityArrayList);
    }
}
