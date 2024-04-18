package com.eventhub.backend.usermanagement.signup.service;

import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.common.entity.EventOrganizerEntity;
import com.eventhub.backend.usermanagement.common.repository.CustomerRepository;
import com.eventhub.backend.usermanagement.common.repository.EventOrganizerRepository;
import com.eventhub.backend.usermanagement.signup.request.SignUpRequest;
import com.eventhub.backend.usermanagement.signup.response.SignUpResponse;
import com.eventhub.backend.utils.awsemailservice.EmailMessages;
import com.eventhub.backend.utils.awsemailservice.SubscribeEmailToSNSTopic;
import com.eventhub.backend.utils.enums.UserRole;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.eventhub.backend.utils.imageservice.S3CloudFront;
import com.eventhub.backend.utils.jwt.JWTAuthentication;
import com.eventhub.backend.utils.passwordencrcyption.PasswordEncypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;

    @Autowired
    JWTAuthentication jwtAuthentication;

    @Autowired
    SubscribeEmailToSNSTopic subscribeToEmailService;

    @Autowired
    EmailMessages emailMessages;
    @Autowired
    S3CloudFront s3CloudFront;

    @Override
    public HttpResponseSuccess<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        System.out.println("signup called : " + signUpRequest);
        String email = signUpRequest.getEmail();
        Optional<?> existingEntityOptional = null;
        String userType = signUpRequest.getUserType();

        if (userType.equals("User")) {
            existingEntityOptional = customerRepository.findByEmail(email);
        } else {
            existingEntityOptional = eventOrganizerRepository.findByEmail(email);
        }

        if (existingEntityOptional.isPresent()) {
            return new HttpResponseSuccess<>(HttpStatus.BAD_REQUEST.value(), "User already exists", null);
        } else {
            if (userType.toLowerCase().equals(UserRole.user.toString())) {
                String filePath = "user/"+signUpRequest.getEmail()+ "/";
                String imageUrl = s3CloudFront.uploadImageGetUrl(filePath, signUpRequest.getImage());
                CustomerEntity user = new CustomerEntity();
                user.setName(signUpRequest.getName());
                user.setEmail(signUpRequest.getEmail());
                user.setAge(signUpRequest.getAge());
                user.setGender(signUpRequest.getGender());
                user.setMobileNumber(signUpRequest.getMobileNumber());
                user.setCity(signUpRequest.getCity());
                user.setProfilePictureUrl(imageUrl);

                user.setPassword(PasswordEncypter.encodePassword(signUpRequest.getPassword()));
                CustomerEntity savedUser = customerRepository.save(user);

//                subscribeToEmailService.subscribeToEmailService(user.getEmail());

                SignUpResponse signUpResponse = new SignUpResponse();
                signUpResponse.setName(user.getName());
                signUpResponse.setEmail(user.getEmail());
                signUpResponse.setJwtToken(jwtAuthentication.createToken(user.getId(), user.getName()));
                signUpResponse.setId(savedUser.getId());
                signUpResponse.setUserType(userType);

                return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Account created Successfully", signUpResponse);
            } else {
                String filePath = "event-organizer/"+signUpRequest.getEmail()+ "/";
                String imageUrl = s3CloudFront.uploadImageGetUrl(filePath, signUpRequest.getImage());
                EventOrganizerEntity eventOrganizer = new EventOrganizerEntity();
                eventOrganizer.setName(signUpRequest.getName());
                eventOrganizer.setEmail(signUpRequest.getEmail());
                eventOrganizer.setMobileNumber(signUpRequest.getMobileNumber());
                eventOrganizer.setBusinessName(signUpRequest.getBusinessName());
                eventOrganizer.setProfilePictureUrl(imageUrl);

                eventOrganizer.setPassword(PasswordEncypter.encodePassword(signUpRequest.getPassword()));
                EventOrganizerEntity savedUser = eventOrganizerRepository.save(eventOrganizer);

//                subscribeToEmailService.subscribeToEmailService(eventOrganizer.getEmail());

                SignUpResponse signUpResponse = new SignUpResponse();
                signUpResponse.setName(eventOrganizer.getName());
                signUpResponse.setEmail(eventOrganizer.getEmail());
                signUpResponse.setJwtToken(jwtAuthentication.createToken(eventOrganizer.getId(), eventOrganizer.getName()));
                signUpResponse.setId(savedUser.getId());
                signUpResponse.setUserType(userType);

                return new HttpResponseSuccess<>(HttpStatus.OK.value(), "Account created Successfully", signUpResponse);
            }
        }
    }
}
