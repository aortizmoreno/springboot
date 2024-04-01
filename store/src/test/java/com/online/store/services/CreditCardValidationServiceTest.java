package com.online.store.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.online.store.exception.CreditCardValidationException;

@ExtendWith(MockitoExtension.class)
public class CreditCardValidationServiceTest {

    @InjectMocks
    CreditCardValidationService creditCardValidationService;

    @Test
    void testValidate() {
        assertThrows(CreditCardValidationException.class, () -> {
            creditCardValidationService.validate("1111");
        });

        assertThrows(CreditCardValidationException.class, () -> {
            creditCardValidationService.validate("1111111111111111");
        });

        assertDoesNotThrow(() -> {
            creditCardValidationService.validate("1111222233334444"); 
        });
    }
}
