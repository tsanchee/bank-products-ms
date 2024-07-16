package com.bank.products.document;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "virtualWallet")
public class VirtualWallet extends Product{

	private static final long serialVersionUID = 1L;
	
	private String phoneNumber;
}
