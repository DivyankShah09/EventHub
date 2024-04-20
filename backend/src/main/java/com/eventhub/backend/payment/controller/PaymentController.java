package com.eventhub.backend.payment.controller;

import com.eventhub.backend.payment.request.PaymentRequest;
import com.eventhub.backend.payment.response.PaymentResponse;
import com.eventhub.backend.payment.service.PaymentService;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/save-payment")
    public HttpResponseSuccess<PaymentResponse> savePayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPaymentLink(paymentRequest);
    }
}
