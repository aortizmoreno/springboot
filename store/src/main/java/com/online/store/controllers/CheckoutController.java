package com.online.store.controllers;

import java.util.HashSet;
import java.util.Set;
import org.springframework.web.bind.annotation.RestController;
import com.online.store.exception.CreditCardValidationException;
import com.online.store.models.CheckoutRequest;
import com.online.store.services.CreditCardValidationService;
import com.online.store.services.OrdersService;
import com.online.store.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.online.store.models.Order;

@RestController
public class CheckoutController {

    private final OrdersService orderService;
    private final ProductService productService;
    private final CreditCardValidationService creditCardValidationService;

    public CheckoutController(OrdersService ordersService, ProductService productService,
            CreditCardValidationService creditCardValidationService) {
        this.orderService = ordersService;
        this.productService = productService;
        this.creditCardValidationService = creditCardValidationService;
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(@Valid @RequestBody CheckoutRequest checkoutRequest) {

        Set<Order> orders = new HashSet<>(checkoutRequest.getProducts().size());

        if (checkoutRequest.getCreditCard() == null || checkoutRequest.getCreditCard().isBlank()) {
            return new ResponseEntity<>("Credit card information is missing", HttpStatus.PAYMENT_REQUIRED);
        }

        if (checkoutRequest.getFirstName() == null || checkoutRequest.getFirstName().isBlank()) {
            return new ResponseEntity<>("First name is missing", HttpStatus.BAD_REQUEST);
        }

        if (checkoutRequest.getLastName() == null || checkoutRequest.getLastName().isBlank()) {
            return new ResponseEntity<>("Last name is missing", HttpStatus.BAD_REQUEST);
        }

        creditCardValidationService.validate(checkoutRequest.getCreditCard());

        for (CheckoutRequest.ProductInfo productInfo : checkoutRequest.getProducts()) {
            Order order = new Order(
                    checkoutRequest.getFirstName(),
                    checkoutRequest.getLastName(),
                    checkoutRequest.getEmail(),
                    checkoutRequest.getShippingAddress(),
                    productInfo.getQuantity(),
                    productService.getProductById(productInfo.getProductId()),
                    checkoutRequest.getCreditCard());
            orders.add(order);
        }

        if (orders.size() > 0){
            orderService.placeOrders(orders);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ExceptionHandler({ CreditCardValidationException.class })
    public ResponseEntity<String> handlerCreditCardError(Exception ex) {
        System.out.println(String.format("Request to /checkout path threw an exception %s", ex.getMessage()));
        return new ResponseEntity<>("Credit card is invalid, please use another form of payment",
                HttpStatus.BAD_REQUEST);
    }

}
