package com.eventhub.backend.event.request;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;

public class EventRequest {
    private String name;

    private String description;

    private MultipartFile image;

    private Date date;

    private Time time;

    private String location;

    private String price;

    public EventRequest() {
    }

    public EventRequest(String name, String description, MultipartFile image, Date date, Time time, String location, String price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.date = date;
        this.time = time;
        this.location = location;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
