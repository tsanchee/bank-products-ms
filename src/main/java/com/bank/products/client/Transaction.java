package com.bank.products.client;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String productId;
	private String transType;
	private String transReason;
	private String paymentMethod;
	private String toProductId;
	private boolean amount;
	private boolean commision;
	private String createdAt;
	private String createdBy;
	private String updatedAt;
	private String updatedBy;
	private String status;

}
