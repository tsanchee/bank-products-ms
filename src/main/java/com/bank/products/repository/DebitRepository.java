package com.bank.products.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.products.document.Debit;

import reactor.core.publisher.Flux;

@Repository
public interface DebitRepository extends ReactiveMongoRepository<Debit, String> {
	Flux<Debit> findByProductType(String productType);
	Flux<Debit> findByProductName(String productName);
	Flux<Debit> findByCustomerId(String customerId);
}
