package com.eventhub.backend.payment.entity;

import com.eventhub.backend.booking.entity.BookingEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookingId")
    private BookingEntity bookingEntity;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    @Column(name = "date")
    private Date date;

    public PaymentEntity() {
    }

    public PaymentEntity(BookingEntity bookingEntity, Integer totalPrice, Date date) {
        this.bookingEntity = bookingEntity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookingEntity getBookingEntity() {
        return bookingEntity;
    }

    public void setBookingEntity(BookingEntity bookingEntity) {
        this.bookingEntity = bookingEntity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", bookingEntity=" + bookingEntity +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
