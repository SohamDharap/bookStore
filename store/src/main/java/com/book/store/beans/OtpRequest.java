package com.book.store.beans;

import lombok.Data;

@Data
public class OtpRequest {
    // User can provide either their email OR their mobile number
    private String email;
    private String mobileNumber;
}