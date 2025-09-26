package com.book.store.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBookResponse {
    private String status;
    private int statusCode;
    private String statusMessage;
}