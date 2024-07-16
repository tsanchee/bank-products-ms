package com.bank.products.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document(collection = "product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Field(name = "customerId")
	private String customerId;
	
	@Field(name = "cardId")
	private String cardId;
	
	@Field(name = "productType")
	private String productType;
	
	@Field(name = "linkedTo")
	private String linkedTo;
	
	@Field(name = "productName")
	private String productName;
	
	@Field(name = "hierarchy")
	private int hierarchy;
	
	@Field(name = "currency")
	private String currency;
	
	@Field(name = "initicalAmount")
	private double initicalAmount;
	
	@Field(name = "currentAmount")
	private double currentAmount;
	
	@Field(name = "totalCommission")
	private double totalCommission;
	
	@Field(name = "isActive")
	private boolean isActive;


}
