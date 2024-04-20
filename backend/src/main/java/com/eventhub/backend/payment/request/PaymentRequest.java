package com.eventhub.backend.payment.request;

import java.sql.Date;

public class PaymentRequest {
    private Integer bookingId;
    private Date date;
    private Integer totalPrice;

    public PaymentRequest() {
    }

    public PaymentRequest(Integer bookingId, Date date, Integer totalPrice) {
        this.bookingId = bookingId;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
