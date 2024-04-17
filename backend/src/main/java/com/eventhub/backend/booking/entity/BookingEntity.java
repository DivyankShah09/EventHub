package com.eventhub.backend.booking.entity;

import com.eventhub.backend.authentication.common.entity.CustomerEntity;
import com.eventhub.backend.event.entity.EventEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private CustomerEntity user;

//    @Column(name = "eventId")
//    private Integer eventId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventId")
    private EventEntity event;

    @Column(name = "date")
    private Date date;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    public BookingEntity() {
    }

    public BookingEntity(CustomerEntity user, EventEntity event, Date date, Integer quantity, Integer totalPrice) {
        this.user = user;
        this.event = event;
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerEntity getUser() {
        return user;
    }

    public void setUser(CustomerEntity user) {
        this.user = user;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                ", date=" + date +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
