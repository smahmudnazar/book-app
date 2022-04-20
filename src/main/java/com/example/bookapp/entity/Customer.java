package com.example.bookapp.entity;

import com.example.bookapp.entity.enums.AuthenticationProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email,password, firstName,lastName,phoneNumber,
            addressLine1,addressLine2,city,state,postalCode,verificationCode;

    @Enumerated(EnumType.STRING)
    private AuthenticationProvider authenticationProvider;

    @CreationTimestamp
    private Timestamp createdTime;

    private boolean enabled;
}
