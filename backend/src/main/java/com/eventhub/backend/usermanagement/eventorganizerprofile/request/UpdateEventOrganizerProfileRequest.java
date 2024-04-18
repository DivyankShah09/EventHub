package com.eventhub.backend.usermanagement.eventorganizerprofile.request;

import org.springframework.web.multipart.MultipartFile;

public class UpdateEventOrganizerProfileRequest {
    private Integer id;

    private String name;

    private String businessName;

    private String email;

    private String mobileNumber;

    private MultipartFile profilePicture;

    public UpdateEventOrganizerProfileRequest() {
    }

    public UpdateEventOrganizerProfileRequest(Integer id, String name, String businessName, String email, String mobileNumber, MultipartFile profilePicture) {
        this.id = id;
        this.name = name;
        this.businessName = businessName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.profilePicture = profilePicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "UpdateEventOrganizerProfileRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", businessName='" + businessName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", profilePicture=" + profilePicture +
                '}';
    }
}
