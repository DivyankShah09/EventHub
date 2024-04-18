package com.eventhub.backend.event.entity;

import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "event")
public class EventEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventOrganizerId")
    private EventOrganizerEntity eventOrganizer;

    @Column(name = "name")
    private String name;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private String price;

    public EventEntity() {
    }

    public EventEntity(EventOrganizerEntity eventOrganizer, String name, String imageUrl, String description, Date date, Time time, String location, String price) {
        this.eventOrganizer = eventOrganizer;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventOrganizerEntity getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(EventOrganizerEntity eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", eventOrganizer=" + eventOrganizer +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
