package com.in28minutes.unit.testing.lab.others;

public interface CustomerBusinessService {

	Amount calculateCustomerProductsSum(String id)
			throws DifferentCurrenciesException;

}
