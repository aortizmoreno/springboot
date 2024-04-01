package com.online.store.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class GlobalControllerExceptionHandlerTest {

    @InjectMocks
    GlobalControllerExceptionHandler globalControllerExceptionHandler;

    @Mock
    HttpServletRequest httpServletRequest ;

    @Test
    void testDefaultErrorHandler() throws Exception {
        System.err.println(httpServletRequest);
        Exception exception = new Exception();
        ResponseEntity<String> response = globalControllerExceptionHandler.defaultErrorHandler(httpServletRequest, exception);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
