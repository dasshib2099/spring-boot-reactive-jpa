package com.shib.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_personal")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;
}
