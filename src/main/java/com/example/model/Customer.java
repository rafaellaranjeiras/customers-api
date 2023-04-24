package com.example.model;

import java.util.Date;
import java.util.List;

import com.example.common.enums.CustomerStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<PaymentCard> paymentCards;
    

}
