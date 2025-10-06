package com.fullstack.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long custId;

    private String custName;

    private String custAddress;

    @Column(unique = true)
    private long custContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    @Temporal(TemporalType.DATE)
    private Date custDOB;

    private String custGender;

    @Column(unique = true)
    private String custEmailId;

    private String custPassword;

}
