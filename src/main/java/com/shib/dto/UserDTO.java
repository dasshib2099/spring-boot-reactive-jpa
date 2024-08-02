package com.shib.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String emailId;
    private String mobileNumber;
    private String address;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
