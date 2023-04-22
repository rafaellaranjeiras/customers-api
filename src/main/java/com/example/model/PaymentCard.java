package com.example.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_card")
public class PaymentCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@Column(name="card_number", nullable = false, columnDefinition = "VARCHAR(20)")
	private String cardNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expiration_date", nullable = false, columnDefinition = "TIMESTAMP")
	private Date expirationDate;

	@Column(name="holder_name", nullable = false, columnDefinition = "VARCHAR(100)")
	private String holderName;

	@Column(name="cvv", nullable = false, columnDefinition = "VARCHAR(4)")
	private String cvv;

	@ManyToOne(optional = false)
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	private CardBrand brand;	
	
}
