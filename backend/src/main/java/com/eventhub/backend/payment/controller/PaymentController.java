package com.eventhub.backend.payment.controller;

import com.eventhub.backend.payment.request.PaymentRequest;
import com.eventhub.backend.payment.response.PaymentResponse;
import com.eventhub.backend.payment.service.PaymentService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/make-payment")
    public HttpResponseSuccess<PaymentResponse> createPaymentLink(@RequestBody PaymentRequest paymentRequest, HttpServletRequest request) {
        return paymentService.createPaymentLink(paymentRequest, request);
    }

    @PostMapping(value = "/save-payment", params = "booking-id")
    public HttpResponseSuccess<?> savePayment(@RequestParam(name = "booking-id") Integer bookingId, HttpServletRequest request) {
        return paymentService.savePayment(bookingId, request);
    }
}
