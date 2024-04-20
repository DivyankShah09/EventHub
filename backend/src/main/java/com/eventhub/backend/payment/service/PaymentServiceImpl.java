package com.eventhub.backend.payment.service;

import com.eventhub.backend.booking.entity.BookingEntity;
import com.eventhub.backend.booking.repository.BookingRepository;
import com.eventhub.backend.payment.entity.PaymentEntity;
import com.eventhub.backend.payment.repository.PaymentRespository;
import com.eventhub.backend.payment.request.PaymentRequest;
import com.eventhub.backend.payment.response.PaymentResponse;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.eventhub.backend.utils.jwt.JWTAuthentication;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    JWTAuthentication jwtAuthentication;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    PaymentRespository paymentRespository;
    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public HttpResponseSuccess<PaymentResponse> createPaymentLink(PaymentRequest paymentRequest, HttpServletRequest request) {
        try {
            String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
            Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
            if (userId != null) {
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

                System.out.println("url: " + paymentResponse.getPaymentUrl());

                return new HttpResponseSuccess<PaymentResponse>(HttpStatus.OK.value(), "Payment URL Generated", paymentResponse);
            } else {
                return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                        null);
            }
        } catch (Exception e) {
            return new HttpResponseSuccess<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<?> savePayment(Integer bookingId, HttpServletRequest request) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer userId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        System.out.println("uid: " + userId);
        if (userId != null) {
            System.out.println("sp: " + bookingId);
            BookingEntity bookingEntity = bookingRepository.findById(bookingId).get();
            PaymentEntity paymentEntity = new PaymentEntity();
            paymentEntity.setBookingEntity(bookingEntity);
            paymentEntity.setDate(bookingEntity.getDate());
            paymentEntity.setTotalPrice(bookingEntity.getTotalPrice());

            paymentRespository.save(paymentEntity);
            return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Payment Successful",
                    "Success");
        } else {
            return new HttpResponseSuccess<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }
}
