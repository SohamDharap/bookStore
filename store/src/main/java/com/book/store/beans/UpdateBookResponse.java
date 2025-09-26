package com.book.store.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.book.store.entity.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookResponse {
    private Book book;
    private boolean updated;
    private String status;
    private int statusCode;
    private String statusMessage;
}
