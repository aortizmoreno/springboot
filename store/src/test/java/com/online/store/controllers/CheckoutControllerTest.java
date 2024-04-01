package com.online.store.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.online.store.exception.CreditCardValidationException;
import com.online.store.models.CheckoutRequest;
import com.online.store.models.CheckoutRequest.ProductInfo;
import com.online.store.repositories.OrderRepository;
import com.online.store.services.CreditCardValidationService;
import com.online.store.services.OrdersService;
import com.online.store.services.ProductService;

public class CheckoutControllerTest {

    OrderRepository orderRepository;

    OrdersService orderService;
    ProductService productService;
    CreditCardValidationService creditCardValidationService;
    CheckoutController checkoutController;

    @BeforeEach
    void setUp() {
        orderService = new OrdersService(orderRepository, 10);
        creditCardValidationService = new CreditCardValidationService();
        checkoutController = new CheckoutController(orderService, productService,
                creditCardValidationService);
    }

    @Test
    void testCheckout_creditCardInformationIsMissing() {
        List<ProductInfo> products = new ArrayList<ProductInfo>();
        CheckoutRequest checkoutRequest = new CheckoutRequest("", "", "", "", products, "");
        ResponseEntity responseEntity = checkoutController.checkout(checkoutRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.PAYMENT_REQUIRED);
    }

    @Test
    void testCheckout_firstNameIsMissing() {
        List<ProductInfo> products = new ArrayList<ProductInfo>();
        CheckoutRequest checkoutRequest = new CheckoutRequest("", "", "", "",
                products, "credit_card");
        ResponseEntity responseEntity = checkoutController.checkout(checkoutRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void testCheckout_LastNameIsMissing() {
        List<ProductInfo> products = new ArrayList<ProductInfo>();
        CheckoutRequest checkoutRequest = new CheckoutRequest("test", "", "", "",
                products, "credit_card");
        ResponseEntity responseEntity = checkoutController.checkout(checkoutRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void testCheckout_callValidateWithError() {
        List<ProductInfo> products = new ArrayList<ProductInfo>();
        CheckoutRequest checkoutRequest = new CheckoutRequest("test", "test", "", "",
                products, "credit_card");
        assertThrows(CreditCardValidationException.class, () -> {
            checkoutController.checkout(checkoutRequest);
        });
    }

    @Test
    void testCheckout_AllGood() {
        List<ProductInfo> products = new ArrayList<ProductInfo>();
        CheckoutRequest checkoutRequest = new CheckoutRequest("test", "test", "", "",
                products, "1111222233334444");
        ResponseEntity responseEntity = checkoutController.checkout(checkoutRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testHandlerCreditCardError() {
        Exception exception = new Exception();
        ResponseEntity responseEntity = checkoutController.handlerCreditCardError(exception);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
