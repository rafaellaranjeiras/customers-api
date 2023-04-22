package com.example.model;

import java.util.Date;

import com.example.common.enums.CustomerStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date", columnDefinition = "TIMESTAMP")
    private Date birthDate;

    @Column(name = "full_name", columnDefinition = ("VARCHAR(100)"))
    private String fullName;
    
    @Column(name = "document_number", columnDefinition = "VARCHAR(11)")
    private String documentNumber;

    @Column(name = "status", columnDefinition = "VARCHAR(1) DEFAULT 'A'")
    private CustomerStatus status;

}
