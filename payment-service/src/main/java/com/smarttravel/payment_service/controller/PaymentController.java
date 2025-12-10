package com.smarttravel.payment_service.controller;

import com.smarttravel.payment_service.dto.PaymentRequestDto;
import com.smarttravel.payment_service.dto.PaymentResponseDto;
import com.smarttravel.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> processPayment(
            @Valid @RequestBody PaymentRequestDto requestDto) {

        PaymentResponseDto response = paymentService.processPayment(requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
