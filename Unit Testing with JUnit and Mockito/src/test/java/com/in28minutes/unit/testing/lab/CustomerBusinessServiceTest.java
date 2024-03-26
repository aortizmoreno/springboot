package com.in28minutes.unit.testing.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.unit.testing.lab.others.Amount;
import com.in28minutes.unit.testing.lab.others.AmountImpl;
import com.in28minutes.unit.testing.lab.others.Currency;
import com.in28minutes.unit.testing.lab.others.CustomerDataService;
import com.in28minutes.unit.testing.lab.others.DifferentCurrenciesException;
import com.in28minutes.unit.testing.lab.others.Product;
import com.in28minutes.unit.testing.lab.others.ProductImpl;
import com.in28minutes.unit.testing.lab.others.ProductType;

@ExtendWith(MockitoExtension.class)
public class CustomerBusinessServiceTest {

	private static final String DUMMY_ID = "DUMMY_ID";

	@Mock
	CustomerDataService customerDataServiceMock;

	@InjectMocks
	CustomerBusinessServiceImpl customerBusinessService;

	private void assertAmount(Amount expected, Amount actual) {
		assertEquals(expected.getCurrency(), actual.getCurrency());
		assertEquals(expected.getValue(), actual.getValue());
	}

	private List<Product> createProductsWithAmounts(Amount[] amounts) {
		return Arrays.stream(amounts)
				.map(amount -> new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE,
						amount))
				.collect(Collectors.toList());
	}

	@Test
	public void testCustomerProductSum_TwoProductSameCurrencies() throws DifferentCurrenciesException {
		Amount[] amounts = {
				new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
				new AmountImpl(new BigDecimal("6.0"), Currency.EURO),
		};

		Amount expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);
		when(customerDataServiceMock.retrieveCustomerProducts(DUMMY_ID)).thenReturn(createProductsWithAmounts(amounts));
		assertAmount(customerBusinessService.calculateCustomerProductsSum(DUMMY_ID), expected);
	}

	@Test
	public void testCustomerProductSum_emptyProducts() throws DifferentCurrenciesException {
		Amount[] amounts = {};

		Amount expected = new AmountImpl(BigDecimal.ZERO, Currency.EURO);
		when(customerDataServiceMock.retrieveCustomerProducts(DUMMY_ID)).thenReturn(createProductsWithAmounts(amounts));
		assertAmount(customerBusinessService.calculateCustomerProductsSum(DUMMY_ID), expected);
	}

	@Test
	public void testCustomerProductSum_TwoProductsDifferenceCurrencies() throws DifferentCurrenciesException {
		Amount[] amounts = {
				new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
				new AmountImpl(new BigDecimal("6.0"), Currency.INDIAN_RUPEE)
		};

		when(customerDataServiceMock.retrieveCustomerProducts(DUMMY_ID)).thenReturn(createProductsWithAmounts(amounts));
		Assertions.assertThrows(DifferentCurrenciesException.class, () -> {
			customerBusinessService.calculateCustomerProductsSum(DUMMY_ID);
		});
	}
}