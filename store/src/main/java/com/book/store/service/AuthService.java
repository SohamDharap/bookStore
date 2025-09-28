package com.book.store.service;

import com.book.store.beans.CreateAccountRequest;
import com.book.store.entity.User;

public interface AuthService {

    User createAccount(CreateAccountRequest request);
    String generateOtp(String email, String mobileNumber);
    String verifyOtpAndLogin(String email, String mobileNumber, String otp);
}