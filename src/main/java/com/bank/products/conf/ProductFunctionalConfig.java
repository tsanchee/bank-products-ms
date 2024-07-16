package com.bank.products.conf;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bank.products.client.Transaction;
import com.bank.products.document.Credit;
import com.bank.products.document.Debit;
import com.bank.products.document.Product;
import com.bank.products.document.VirtualWallet;
import com.bank.products.service.ProductService;

@Configuration
public class ProductFunctionalConfig {
	@Autowired
	private ProductService productService;
	
	@Bean
    RouterFunction<ServerResponse> getCredits(){
		return route(GET("/api/v1/products/credits"), 
		        req -> ok().body(
	        		productService.getCredits(), Credit.class
        		));
    }
	
	@Bean
    RouterFunction<ServerResponse> getCreditById(){
		return route(GET("/api/v1/products/credits/ids/{id}"), 
		        req -> ok().body(
	        		productService.getCreditByProductId(req.pathVariable("id")), Credit.class
        		));
    }
	
	@Bean
    RouterFunction<ServerResponse> getCreditsByProductType(){
		return route(GET("/api/v1/products/credits/types/{productType}"), 
		        req -> ok().body(
	        		productService.getCreditsByProductType(req.pathVariable("productType")), Credit.class
        		));
    }
	
	@Bean
    RouterFunction<ServerResponse> getCreditsByProductName(){
		return route(GET("/api/v1/products/credits/names/{productName}"), 
		        req -> ok().body(
	        		productService.getCreditsByProductName(req.pathVariable("productName")), Credit.class
        		));
    }
	
	@Bean
    RouterFunction<ServerResponse> getCreditsByCustomerId(){
		return route(GET("/api/v1/products/credits/customers/{customerId}"), 
		        req -> ok().body(
	        		productService.getCreditsByCustomerId(req.pathVariable("customerId")), Credit.class
        		));
    }
	
	@Bean
    RouterFunction<ServerResponse> addCredit(){
		return route(POST("/api/v1/products/credits"), 
		        req -> ok().body(
		        		fromPublisher(req.bodyToMono(Credit.class).flatMap(productService::addCredit), Credit.class)
        		));
    }
	
	@Bean
    RouterFunction<ServerResponse> editCredit(){
		return route(PUT("/api/v1/products/credits"), 
				req -> ok().body(
		        		fromPublisher(req.bodyToMono(Credit.class).flatMap(productService::editCredit), Credit.class)
        		));
    }
	
	@Bean
    RouterFunction<ServerResponse> deleteCredit(){
		return route(DELETE("/api/v1/products/credits/{id}"), 
				req -> ok().body(
		        		productService.getCreditByProductId(req.pathVariable("id")).flatMap(credit -> productService.deleteCredit(credit)), Void.class
        		));
    }
	
	//****************************************Product: Debit********************************************//
	
	@Bean
	RouterFunction<ServerResponse> getDebits(){
		return route(GET("/api/v1/products/debits"),
				req -> ok().body(
						productService.getDebits(), Debit.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getDebitByProductId(){
		return route(GET("/api/v1/products/debits/ids/{id}"),
				req -> ok().body(
						productService.getDebitByProductId(req.pathVariable("id")), Debit.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getDebitsByProductType(){
		return route(GET("/api/v1/products/debits/types/{productType}\""), 
				req -> ok().body(
						productService.getDebitsByProductType(req.pathVariable("productType")), Debit.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getDebitsByProductName(){
		return route(GET("/api/v1/products/debits/names/{productName}"),
				req -> ok().body(
						productService.getDebitsByProductName(req.pathVariable("productName")), Debit.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getDebitsByCustomerId(){
		return route(GET("/api/v1/products/debits/customers/{customerId}"),
				req -> ok().body(
						productService.getDebitsByCustomerId(req.pathVariable("customerId")), Debit.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> addDebit(){
		return route(POST("/api/v1/products/debits"),
				req -> ok().body(
						fromPublisher(req.bodyToMono(Debit.class).flatMap(productService::addDebit), Debit.class)
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> editDebit(){
		return route(PUT("/api/v1/products/debits"),
				req -> ok().body(
						fromPublisher(req.bodyToMono(Debit.class).flatMap(productService::editDebit), Debit.class)
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> deletedDebit(){
		return route(DELETE("/api/v1/products/debits/{id}"),
				req -> ok().body(
						productService.getDebitByProductId(req.pathVariable("id")).flatMap(Debit -> productService.deleteDebit(Debit)), Void.class
						));
	}
	
	//****************************************Product: VirtualWallet********************************************//
	
	@Bean
	RouterFunction<ServerResponse> getVirtualWallets(){
		return route(GET("/api/v1/products/wallets"),
				req -> ok().body(
						productService.getVirtualWallets(), VirtualWallet.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getVirtualWalletByProductId(){
		return route(GET("/api/v1/products/wallets/ids/{id}"),
				req -> ok().body( 
						productService.getVirtualWalletByProductId("id"), VirtualWallet.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getVirtualWalletsByProductType(){
		return route(GET("/api/v1/products/wallets/types/{productType}"),
				req -> ok().body( 
						productService.getVirtualWalletsProductType("productType"), VirtualWallet.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getVirtualWalletsByProductName(){
		return route(GET("/api/v1/products/wallets/names/{productName}"), 
				req -> ok().body( 
						productService.getVirtualWalletsByProductName("productName"), VirtualWallet.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getVirtualWalletsByCustomerId(){
		return route(GET("/api/v1/products/wallets/customers/{customerId}"),
				req -> ok().body( 
						productService.getVirtualWalletsByCustomerId("customerId"), VirtualWallet.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> addVirtualWallet(){
		return route(POST("/api/v1/products/wallets"), 
				req -> ok().body( 
						fromPublisher(req.bodyToMono(VirtualWallet.class).flatMap(productService::addVirtualWallet), VirtualWallet.class)
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> editVirtualWallet(){
		return route(PUT("/api/v1/products/wallets"),
				req -> ok().body(
						fromPublisher(req.bodyToMono(VirtualWallet.class).flatMap(productService::editVirtualWallet), VirtualWallet.class)
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> deletedVirtualWallet(){
		return route(DELETE("/api/v1/products/wallets/{id}"),
				req -> ok().body(
						productService.getVirtualWalletByProductId(req.pathVariable("id")).flatMap(VirtualWallet -> productService.deleteVirtualWallet(VirtualWallet)),Void.class
						));
	}
	
	//***********************************Reports*****************************************//
	
	@Bean
	RouterFunction<ServerResponse> getProductsByCustomerId(){
		return route(GET("/api/v1/products/customers/{id}"),
				req -> ok().body(
						productService.getProductsByCustomerId("id"), Product.class
						));
	}
	
	@Bean
	RouterFunction<ServerResponse> getTransactionsByCustomerId(){
		return route(GET("/api/v1/products/customers/{id}/transactions"),
				req -> ok().body(
						productService.getTransactionsByCustomerId("id"), Transaction.class
						));
	}
}
