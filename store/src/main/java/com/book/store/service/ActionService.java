package com.book.store.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.book.store.entity.Book;
import com.book.store.beans.AddBookRequest;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public interface ActionService {
    Book addBook(AddBookRequest request);
    Book getBookById(Integer id);
    Book updateBook(Integer id, Book updatedBook);
    boolean deleteBook(Integer id);
    List<Book> getAllBooks();
}