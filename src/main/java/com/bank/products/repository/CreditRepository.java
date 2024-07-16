package com.bank.products.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.products.document.Credit;

import reactor.core.publisher.Flux;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
	Flux<Credit> findByProductType(String productType);
	Flux<Credit> findByProductName(String productName);
	Flux<Credit> findByCustomerId(String customerId);
}
