package com.bank.products.document;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "credit")
public class Credit extends Product{

	private static final long serialVersionUID = 1L;
	
	private int maxMonthTrans;
	private int currentTrans;
	private String transDay;
	private boolean isLimited;
	private double interest;
	private int duesNumber;
	private double totalAmount;
	private String expiresAt;
}
