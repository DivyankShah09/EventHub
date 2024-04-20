package com.eventhub.backend.payment.request;

import com.eventhub.backend.event.entity.EventEntity;
import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;

import java.sql.Date;

public class PaymentRequest {
    private Integer bookingId;

    private CustomerEntity user;

    private EventEntity event;

    private Date date;

    private Integer quantity;

    private Integer totalPrice;

    public PaymentRequest() {
    }

    public PaymentRequest(Integer bookingId, CustomerEntity user, EventEntity event, Date date, Integer quantity, Integer totalPrice) {
        this.bookingId = bookingId;
        this.user = user;
        this.event = event;
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
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
        return "PaymentRequest{" +
                "bookingId=" + bookingId +
                ", user=" + user +
                ", event=" + event +
                ", date=" + date +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
