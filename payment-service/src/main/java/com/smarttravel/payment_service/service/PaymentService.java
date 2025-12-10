package com.smarttravel.payment_service.service;

import com.smarttravel.payment_service.dto.PaymentRequestDto;
import com.smarttravel.payment_service.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto processPayment(PaymentRequestDto requestDto);
}
