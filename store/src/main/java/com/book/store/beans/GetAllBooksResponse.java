package com.book.store.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.book.store.entity.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBooksResponse {
    private List<Book> books;
    private String status;
    private int statusCode;
    private String statusMessage;
}