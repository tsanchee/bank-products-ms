package com.bank.products.service;

import com.bank.products.client.Transaction;
import com.bank.products.document.Credit;
import com.bank.products.document.Debit;
import com.bank.products.document.Product;
import com.bank.products.document.VirtualWallet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	
	public Flux<Product> getProductsByCustomerId(String customerId);
	public Flux<Transaction> getTransactionsByCustomerId(String customerId);

	public Flux<Debit> getDebits();
	public Mono<Debit> getDebitByProductId(String id);
	public Flux<Debit> getDebitsByProductType(String productType);
	public Flux<Debit> getDebitsByProductName(String productName);
	public Flux<Debit> getDebitsByCustomerId(String customerId);
	public Mono<Debit> addDebit(Debit debit);
	public Mono<Debit> editDebit(Debit debit);
	public Mono<Void> deleteDebit(Debit debit);
	
	public Flux<Credit> getCredits();
	public Mono<Credit> getCreditByProductId(String id);
	public Flux<Credit> getCreditsByProductType(String productType);
	public Flux<Credit> getCreditsByProductName(String productName);
	public Flux<Credit> getCreditsByCustomerId(String customerId);
	public Mono<Credit> addCredit(Credit credit);
	public Mono<Credit> editCredit(Credit credit);
	public Mono<Void> deleteCredit(Credit credit);
	
	public Flux<VirtualWallet> getVirtualWallets();
	public Mono<VirtualWallet> getVirtualWalletByProductId(String id);
	public Flux<VirtualWallet> getVirtualWalletsProductType(String productType);
	public Flux<VirtualWallet> getVirtualWalletsByProductName(String productName);
	public Flux<VirtualWallet> getVirtualWalletsByCustomerId(String customerId);
	public Mono<VirtualWallet> addVirtualWallet(VirtualWallet virtualWallet);
	public Mono<VirtualWallet> editVirtualWallet(VirtualWallet virtualWallet);
	public Mono<Void> deleteVirtualWallet(VirtualWallet virtualWallet);
}
