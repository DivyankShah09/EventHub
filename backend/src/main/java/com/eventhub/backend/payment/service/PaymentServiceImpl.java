package com.eventhub.backend.payment.service;

import com.eventhub.backend.payment.request.PaymentRequest;
import com.eventhub.backend.payment.response.PaymentResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public HttpResponseSuccess<PaymentResponse> createPaymentLink(PaymentRequest paymentRequest) {
        try {
            System.out.println("pr: " + paymentRequest);
            Stripe.apiKey = stripeSecretKey;
            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3005/payment-success/id=" + paymentRequest.getBookingId())
                    .setCancelUrl("http://localhost:3005/payment-failure/id=" + paymentRequest.getBookingId())
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams
                                                    .LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount((long) paymentRequest.getTotalPrice() * 100)
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("EventHub")
                                                                    .build())
                                                    .build())
                                    .build())
                    .build();
            Session session = Session.create(params);
            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setPaymentUrl(session.getUrl());

            return new HttpResponseSuccess<PaymentResponse>(HttpStatus.OK.value(), "Payment URL Generated", paymentResponse);
        } catch (Exception e) {
            return null;
        }
    }
}
