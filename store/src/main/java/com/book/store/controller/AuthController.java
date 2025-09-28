package com.book.store.controller;

import com.book.store.beans.CreateAccountRequest;
import com.book.store.beans.VerifyOtpResponse;
import com.book.store.beans.OtpRequest;
import com.book.store.beans.VerifyOtpRequest;
import com.book.store.entity.User;
import com.book.store.service.AuthService; // Changed from ActionService
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest request) {
        try {
            User newUser = authService.createAccount(request);
            log.info("Successfully created new account for user: {}", newUser.getEmail());
            return ResponseEntity.ok("Account created successfully for user: " + newUser.getEmail());
        } catch (RuntimeException e) {
            log.error("Error creating account for email {}: {}", request.getEmail(), e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/generateOtp")
    public ResponseEntity<String> generateOtp(@RequestBody OtpRequest request) {
        String identifier = request.getEmail() != null ? request.getEmail() : request.getMobileNumber();
        log.info("OTP generation request for identifier: {}", identifier);

        String otp = authService.generateOtp(request.getEmail(), request.getMobileNumber());

        // --- OTP SENDING SIMULATION ---
        log.info("SIMULATED OTP SEND: The OTP for {} is: {}", identifier, otp);
        // In a real app, you would send this OTP via SMS or email here.
        // -----------------------------

        return ResponseEntity.ok("OTP has been generated and sent (check server console).");
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<VerifyOtpResponse> verifyOtp(@RequestBody VerifyOtpRequest request) {
        String identifier = request.getEmail() != null ? request.getEmail() : request.getMobileNumber();
        log.info("OTP verification request for identifier: {}", identifier);
        try {
            String jwtToken = authService.verifyOtpAndLogin(request.getEmail(), request.getMobileNumber(), request.getOtp());
            log.info("OTP verification successful for {}. JWT issued.", identifier);
            return ResponseEntity.ok(new VerifyOtpResponse(jwtToken));
        } catch (RuntimeException e) {
            log.warn("OTP verification failed for {}: {}", identifier, e.getMessage());
            // Return the error message in the token field for simplicity
            return ResponseEntity.badRequest().body(new VerifyOtpResponse(e.getMessage()));
        }
    }
}