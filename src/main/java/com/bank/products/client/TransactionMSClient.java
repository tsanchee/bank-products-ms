package com.bank.products.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Component
public class TransactionMSClient {
	@Autowired
	private WebClient webClient;
	
	public Flux<Transaction> findByProductId(String productId)  {
	    return webClient.get()
	        .uri("/api/v1/transactions/products/" + productId)
	        .retrieve()
	        .bodyToFlux(Transaction.class);
	}
}
