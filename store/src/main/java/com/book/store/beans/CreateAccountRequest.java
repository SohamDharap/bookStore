package com.book.store.beans;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String roleName;
}