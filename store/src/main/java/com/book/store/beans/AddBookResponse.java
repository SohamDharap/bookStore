package com.book.store.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.book.store.entity.Book;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookResponse {
    private Book book;
    private String status;
    private int statusCode;
    private String statusMessage;

}
