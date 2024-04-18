package com.eventhub.backend.usermanagement.signup.response;

public class SignUpResponse {
    private Integer id;
    private String name;
    private String email;
    private String jwtToken;
    private String userType;

    public SignUpResponse() {
    }

    public SignUpResponse(String name, String email, String jwtToken) {
        this.name = name;
        this.email = email;
        this.jwtToken = jwtToken;
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
