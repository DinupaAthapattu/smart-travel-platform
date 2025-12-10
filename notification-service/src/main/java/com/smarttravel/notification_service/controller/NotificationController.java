package com.smarttravel.notification_service.controller;

import com.smarttravel.notification_service.dto.NotificationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDto requestDto) {

        System.out.println("Sending notification to user: " + requestDto.getEmail());
        System.out.println("Message: " + requestDto.getMessage());
        System.out.println("Booking ID: " + requestDto.getBookingId());

        return new ResponseEntity<>("Notification processed", HttpStatus.OK);
    }
}
