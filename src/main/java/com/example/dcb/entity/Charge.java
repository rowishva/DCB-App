package com.example.dcb.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trx_charge", uniqueConstraints = @UniqueConstraint(columnNames = { "transaction_id" }))
@Where(clause = "is_deleted=0")
@EqualsAndHashCode(callSuper = false)
public class Charge extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "mobile_no", length = 50, nullable = false)
	private String mobileNo;

	@Column(name = "transaction_id", length = 50, nullable = false)
	private String transactionId;

	@Column(name = "item", length = 50, nullable = false)
	private String item;

	@Column(name = "item_description", length = 250, nullable = false)
	private String itemDescription;

	@Column(name = "balanceType", length = 250, nullable = false)
	private String balanceType;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "currency", length = 10, nullable = false)
	private String currency;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status", length = 20)
	private TransactionStatus transactionStatus;

}
