package com.bank.products.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.products.document.VirtualWallet;

import reactor.core.publisher.Flux;

@Repository
public interface VirtualWalletRepository extends ReactiveMongoRepository<VirtualWallet, String> {
	Flux<VirtualWallet> findByProductType(String productType);
	Flux<VirtualWallet> findByProductName(String productName);
	Flux<VirtualWallet> findByCustomerId(String customerId);
}
