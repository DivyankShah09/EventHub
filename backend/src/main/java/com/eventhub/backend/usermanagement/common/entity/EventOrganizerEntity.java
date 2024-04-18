package com.eventhub.backend.usermanagement.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "event_organizer")
public class EventOrganizerEntity implements UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "profilePictureUrl")
    private String profilePictureUrl;

    public EventOrganizerEntity() {
    }

    public EventOrganizerEntity(String name, String businessName, String email, String mobileNumber, String password, String profilePictureUrl) {
        this.name = name;
        this.businessName = businessName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
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

    @Override
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

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    @Override
    public String toString() {
        return "EventOrganizerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", businessName='" + businessName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                '}';
    }
}
