package com.book.store.serviceImpl;

import com.book.store.service.ActionService;
import com.book.store.entity.Book;
import com.book.store.beans.AddBookRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActionServiceImpl implements ActionService {

    // Simulate a database with a map
    private final Map<Integer, Book> bookStore = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    // Add a new book
    @Override
    public Book addBook(AddBookRequest request) {
        Book book = new Book();
        Integer id = idGenerator.getAndIncrement();
        book.setId(id);
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        bookStore.put(id, book);
        log.info("Book added: {}", book);
        return book;
    }

    // Get a book by its ID
    @Override
    public Book getBookById(Integer id) {
        log.info("Fetching book with id: {}", id);
        return bookStore.get(id);
    }

    // Update a book by its ID
    @Override
    public Book updateBook(Integer id, Book updatedBook) {
        log.info("Updating book with id: {}", id);
        Book existingBook = bookStore.get(id);
        if (existingBook != null) {
            updatedBook.setId(id);
            bookStore.put(id, updatedBook);
            return updatedBook;
        }
        return null;
    }

    // Delete a book by its ID
    @Override
    public boolean deleteBook(Integer id) {
        log.info("Deleting book with id: {}", id);
        return bookStore.remove(id) != null;
    }

    // Get all books
    @Override
    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return new ArrayList<>(bookStore.values());
    }
}