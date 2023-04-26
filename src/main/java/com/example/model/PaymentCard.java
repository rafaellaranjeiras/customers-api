package com.example.model;

import java.time.LocalDate;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date", nullable = false, columnDefinition = "DATE")
	private LocalDate expirationDate;

	@Column(name="holder_name", nullable = false, columnDefinition = "VARCHAR(100)")
	private String holderName;

	@Column(name="cvv", nullable = false, columnDefinition = "VARCHAR(4)")
	private String cvv;

	@ManyToOne(optional = false)
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	private CardBrand brand;	
	
}
