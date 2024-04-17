package com.eventhub.backend.utils.awsemailservice;

import com.eventhub.backend.booking.entity.BookingEntity;
import com.eventhub.backend.event.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EmailMessages {
    private String newEventEmailSubject;
    private String newEventEmailMessage;

    private String bookingEmailSubject;
    private String bookingEmailMessage;

    public String getNewEventEmailSubject(String cityName) {
        newEventEmailSubject = String.format("Exciting News: New Event in %s", cityName);

        return newEventEmailSubject;
    }

    public String getNewEventEmailMessage(String userName, String cityName, EventEntity eventEntity) {

        newEventEmailMessage = String.format(
                "Dear %s \n"
                        + "We hope this message finds you well and filled with anticipation for the latest happenings in %s! \n\n" +
                        "We are thrilled to announce new Event in your city. \n\n" +
                        "Here's a sneak peek of what you can expect: \n" +
                        "- Event Name: %s\n" +
                        "- Date: %s\n" +
                        "- Time: %s\n" +
                        "- Location: %s\n" +
                        "- Description: %s\n\n" +
                        "For any inquiries or further information, feel free to reach out to us. We're here to ensure you have all the details you need to make the most of this upcoming event.\n\n" +
                        "Thank you for being a valued member of our community. We can't wait to see you at the event!\n\n" +
                        "Best regards,\n" +
                        "EventHub Team.",

                userName, cityName, eventEntity.getName(), eventEntity.getDate().toString(), eventEntity.getTime().toString(), eventEntity.getLocation(), eventEntity.getDescription()
        );


        return newEventEmailMessage;
    }

    public String getBookingEmailSubject() {
        bookingEmailSubject = "Event Ticket Booking Confirmation";

        return bookingEmailSubject;
    }

    public String getBookingEmailMessage(String userName, String eventName, BookingEntity bookingEntity) {

        bookingEmailMessage = String.format(
                "Dear %s,\n\n" +
                        "We are thrilled to confirm your ticket booking for the upcoming event: %s!\n\n" +
                        "Here are the details of your booking:\n" +
                        "Booking ID: %s\n" +
                        "Event Name: %s\n" +
                        "Booking Date: %s\n" +
                        "Ticket Quantity: %s\n" +
                        "Total Price: %s\n\n" +
                        "We're delighted that you've chosen to be part of this exciting event. Your booking is now confirmed, and we can't wait to see you there.\n" +
                        "\n" +
                        "If you have any questions or require further assistance, feel free to reach out to us. We're here to ensure you have a seamless experience leading up to the event.\n" +
                        "\n" +
                        "Best regards,\n" +
                        "EventHub Team.", userName, eventName, bookingEntity.getId().toString(), eventName, bookingEntity.getDate(), bookingEntity.getQuantity(), bookingEntity.getTotalPrice());

        return bookingEmailMessage;
    }
}
