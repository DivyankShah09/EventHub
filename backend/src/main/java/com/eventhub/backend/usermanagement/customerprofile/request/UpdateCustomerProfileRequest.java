package com.eventhub.backend.usermanagement.customerprofile.request;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

public class UpdateCustomerProfileRequest {
    private Integer id;

    private String name;

    private String email;

    private String mobileNumber;

    private int age;

    private String gender;

    private String city;

    private MultipartFile profilePicture;

    public UpdateCustomerProfileRequest() {
    }

    public UpdateCustomerProfileRequest(Integer id, String name, String email, String mobileNumber, int age, String gender, String city, MultipartFile profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.gender = gender;
        this.city = city;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "UpdateCustomerProfileRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", profilePicture=" + profilePicture +
                '}';
    }
}
