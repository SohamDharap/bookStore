package com.book.store.service;

import com.book.store.entity.Book;
import java.util.List;
import java.util.Optional; // Add this import

// This is the corrected contract for your service
public interface ActionService {

    Book addBook(Book book);

    // Return Optional<Book> to gracefully handle cases where the book is not found
    Optional<Book> getBookById(Integer id);

    List<Book> getAllBooks();

    Book updateBook(Integer id, Book bookDetails);

    // The delete method in the repository returns void, so this should too
    void deleteBook(Integer id);
}