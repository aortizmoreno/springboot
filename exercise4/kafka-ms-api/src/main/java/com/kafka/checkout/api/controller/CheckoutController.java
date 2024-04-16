package com.kafka.checkout.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kafka.checkout.api.exception.CreditCardValidationException;
import com.kafka.checkout.api.models.CheckoutRequest;
import com.kafka.checkout.api.models.Order;
import com.kafka.checkout.api.service.CreditCardValidationService;
import com.kafka.checkout.api.service.OrdersService;
import com.kafka.checkout.api.service.ProductService;
import com.kafka.checkout.broker.producer.CheckoutProducer;
import java.util.Set;
import javax.validation.Valid;
import java.util.HashSet;

@RestController
public class CheckoutController {

    private final OrdersService orderService;
    private final ProductService productService;
    private final CreditCardValidationService creditCardValidationService;

    @Autowired
	private CheckoutProducer checkoutProducer;

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
                    checkoutProducer.publish(order);
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
