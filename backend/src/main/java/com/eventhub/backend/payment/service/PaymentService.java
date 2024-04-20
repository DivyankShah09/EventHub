package com.eventhub.backend.payment.service;

import com.eventhub.backend.payment.request.PaymentRequest;
import com.eventhub.backend.payment.response.PaymentResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;

public interface PaymentService {
    HttpResponseSuccess<PaymentResponse> createPaymentLink(PaymentRequest paymentRequest);


}
