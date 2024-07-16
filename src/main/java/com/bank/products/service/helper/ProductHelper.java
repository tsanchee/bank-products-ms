package com.bank.products.service.helper;

import java.time.LocalDate;
import java.util.List;

import com.bank.products.document.Credit;

public class ProductHelper {
	
	public static boolean checkExiperedPayments(List<Credit> credits) {
		boolean isExpired = false;
		for (Credit credit : credits) {
			if(credit.getExpiresAt() != null && LocalDate.parse(credit.getExpiresAt()).isBefore(LocalDate.now()) && credit.getTotalAmount() > 0) {
				isExpired = true;
				break;
			}
		}
		return isExpired;
	}
	
}
