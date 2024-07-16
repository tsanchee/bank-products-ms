package com.bank.products.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.products.document.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

	Flux<Product> findByProductType(String productType);
	Flux<Product> findByProductName(String productName);
	Mono<Product> findByCustomerId(String customerId);

}
