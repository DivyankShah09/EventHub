package com.eventhub.backend.usermanagement.customerprofile.service;

import com.eventhub.backend.event.entity.EventEntity;
import com.eventhub.backend.usermanagement.common.entity.CustomerEntity;
import com.eventhub.backend.usermanagement.common.repository.CustomerRepository;
import com.eventhub.backend.usermanagement.customerprofile.request.UpdateCustomerProfileRequest;
import com.eventhub.backend.utils.httpresponse.HttpResponseSuccess;
import com.eventhub.backend.utils.imageservice.S3CloudFront;
import com.eventhub.backend.utils.jwt.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    JWTAuthentication jwtAuthentication;
    @Autowired
    S3CloudFront s3CloudFront;

    @Override
    public HttpResponseSuccess<CustomerEntity> getCustomerDetails(HttpServletRequest request, Integer userId) {

        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer tokenUserId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);

        if(tokenUserId != null && tokenUserId == userId){
            Optional<CustomerEntity> customerEntity = customerRepository.findById(userId);
            return new HttpResponseSuccess<CustomerEntity>(HttpStatus.OK.value(), "User details fetched successfully",
                    customerEntity.get());
        } else {
            return new HttpResponseSuccess<CustomerEntity>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }

    @Override
    public HttpResponseSuccess<CustomerEntity> updateCustomerProfileDetails(UpdateCustomerProfileRequest updateCustomerProfileRequest, HttpServletRequest request) {
        String bearerToken = jwtAuthentication.extractJwtFromRequest(request);
        Integer tokenUserId = jwtAuthentication.validateJWTTokenAndGetUserId(bearerToken);
        if(tokenUserId != null){
            CustomerEntity customerEntity = customerRepository.findById(updateCustomerProfileRequest.getId()).get();
            customerEntity.setName(updateCustomerProfileRequest.getName());
            customerEntity.setMobileNumber(updateCustomerProfileRequest.getMobileNumber());
            customerEntity.setGender(updateCustomerProfileRequest.getGender());
            customerEntity.setAge(updateCustomerProfileRequest.getAge());
            customerEntity.setCity(customerEntity.getCity());

            String filePath = "event-organizer/"+updateCustomerProfileRequest.getEmail()+ "/";
            if(updateCustomerProfileRequest.getProfilePicture() != null) {
                String imageUrl = s3CloudFront.uploadImageGetUrl(filePath, updateCustomerProfileRequest.getProfilePicture());
                customerEntity.setProfilePictureUrl(imageUrl);
            }

            customerRepository.save(customerEntity);

            return new HttpResponseSuccess<CustomerEntity>(HttpStatus.OK.value(), "User details updated successfully",
                    customerEntity);
        } else {
            return new HttpResponseSuccess<CustomerEntity>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized access",
                    null);
        }
    }
}
