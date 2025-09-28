package com.book.store.beans;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String email;
    private String mobileNumber;
    private String otp;
}