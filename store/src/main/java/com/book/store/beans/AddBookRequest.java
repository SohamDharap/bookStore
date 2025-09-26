package com.book.store.beans;

import java.math.BigDecimal;

import com.book.store.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
}