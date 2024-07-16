package com.bank.products.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.products.client.Transaction;
import com.bank.products.client.TransactionMSClient;
import com.bank.products.document.Credit;
import com.bank.products.document.Debit;
import com.bank.products.document.Product;
import com.bank.products.document.VirtualWallet;
import com.bank.products.repository.CreditRepository;
import com.bank.products.repository.DebitRepository;
import com.bank.products.repository.VirtualWalletRepository;
import com.bank.products.service.ProductService;
import com.bank.products.service.helper.ProductHelper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private DebitRepository debitRepository;
	
	@Autowired
	private CreditRepository creditRepository;
	
	@Autowired
	private VirtualWalletRepository virtualWalletRepository;

	
	@Autowired
	private TransactionMSClient transactionMSClient; 
	
	//**********************************Product: Debit*****************************************//

	@Transactional(readOnly = true)
	public Flux<Debit> getDebits() {
		log.info("getDebits");
		return debitRepository.findAll();
	}

	@Transactional
	public Mono<Debit> getDebitByProductId(String id) {
		log.info("id: ", id);
		return debitRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Flux<Debit> getDebitsByProductType(String productType) {
		log.info("productType: ", productType);
		return debitRepository.findByProductType(productType);
	}

	@Transactional(readOnly = true)
	public Flux<Debit> getDebitsByProductName(String productName) {
		log.info("productName: ", productName);
		return debitRepository.findByProductName(productName);
	}

	@Transactional(readOnly = true)
	public Flux<Debit> getDebitsByCustomerId(String customerId) {
		log.info("customerId: ", customerId);
		return debitRepository.findByCustomerId(customerId);
	}

	@Transactional
	public Mono<Debit> addDebit(Debit debit) {
		log.info("productName: ", debit.getProductName());
		return debitRepository.save(debit);
	}

	@Transactional
	public Mono<Debit> editDebit(Debit debit) {
		log.info("productName: ", debit.getProductName());
		return debitRepository.save(debit);
	}

	@Transactional
	public Mono<Void> deleteDebit(Debit debit) {
		log.info("productName: ", debit.getProductName());
		return debitRepository.delete(debit);
	}

	//**********************************Product: Credit*****************************************//
	
	@Transactional(readOnly = true)
	public Flux<Credit> getCredits() {
		return creditRepository.findAll();
	}

	@Transactional
	public Mono<Credit> getCreditByProductId(String id) {
		return creditRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Flux<Credit> getCreditsByProductType(String productType) {
		return creditRepository.findByProductType(productType);
	}

	@Transactional(readOnly = true)
	public Flux<Credit> getCreditsByProductName(String productName) {
		return creditRepository.findByProductName(productName);
	}

	@Transactional(readOnly = true)
	public Flux<Credit> getCreditsByCustomerId(String customerId) {
		return creditRepository.findByCustomerId(customerId);
	}

	@Transactional
	public Mono<Credit> addCredit(Credit credit) {
		Mono<List<Credit>> monoCredits = getCreditsByCustomerId(credit.getCustomerId()).collectList();
		Mono<Boolean> isExpired = monoCredits.flatMap(c -> Mono.just(ProductHelper.checkExiperedPayments(c)));
		isExpired.subscribe(c -> {
			System.out.println(c.toString());
		});
		return isExpired.flatMap(expired -> expired ? Mono.empty() : creditRepository.save(credit));
	}
	
	@Transactional
	public Mono<Credit> editCredit(Credit credit) {
		return creditRepository.save(credit);
	}

	@Transactional
	public Mono<Void> deleteCredit(Credit credit) {
		return creditRepository.delete(credit);
	}

	//**********************************Product: VirtualWallet*****************************************//
	
	@Transactional(readOnly = true)
	public Flux<VirtualWallet> getVirtualWallets() {
		return virtualWalletRepository.findAll();
	}

	@Transactional
	public Mono<VirtualWallet> getVirtualWalletByProductId(String id) {
		return virtualWalletRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Flux<VirtualWallet> getVirtualWalletsProductType(String productType) {
		return virtualWalletRepository.findByProductType(productType);
	}

	@Transactional(readOnly = true)
	public Flux<VirtualWallet> getVirtualWalletsByProductName(String productName) {
		return virtualWalletRepository.findByProductName(productName);
	}

	@Transactional(readOnly = true)
	public Flux<VirtualWallet> getVirtualWalletsByCustomerId(String customerId) {
		return virtualWalletRepository.findByCustomerId(customerId);
	}

	@Transactional
	public Mono<VirtualWallet> addVirtualWallet(VirtualWallet virtualWallet) {
		return virtualWalletRepository.save(virtualWallet);
	}

	@Transactional
	public Mono<VirtualWallet> editVirtualWallet(VirtualWallet virtualWallet) {
		return virtualWalletRepository.save(virtualWallet);
	}

	@Transactional
	public Mono<Void> deleteVirtualWallet(VirtualWallet virtualWallet) {
		return virtualWalletRepository.delete(virtualWallet);
	}
	
	@Transactional(readOnly = true)
	public Flux<Product> getProductsByCustomerId(String customerId) {
		
		Flux<Product> productDebits = getDebitsByCustomerId(customerId).flatMap(debit -> Flux.just(debit));
		Flux<Product> productCredits = getCreditsByCustomerId(customerId).flatMap(credit -> Flux.just(credit));
		Flux<Product> productWallets = getVirtualWalletsByCustomerId(customerId).flatMap(wallet -> Flux.just(wallet));
		
		Flux<Product> allProducts = Flux.merge(productDebits, productCredits, productWallets);

		return allProducts;
	}
	
	@Transactional(readOnly = true)
	public Flux<Transaction> getTransactionsByCustomerId(String customerId){
		Flux<Product> products = getProductsByCustomerId(customerId);
		Flux<Transaction> transactions = products.flatMap(product -> {
			return transactionMSClient.findByProductId(product.getId());
		});
		return transactions;//TODO range of dates filters
	}

}
