package com.book.store.serviceImpl;

import com.book.store.beans.AddBookRequest;
import com.book.store.entity.Book;
import com.book.store.repository.BooksDao; // Make sure this import is correct
import com.book.store.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActionServiceImpl implements ActionService {

    // Inject the repository to interact with the database
    @Autowired
    private BooksDao bookRepository;

    @Override
    public Book addBook(Book book) {
        log.info("Saving new book to the database: {}", book.getTitle());
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        log.info("Fetching book with id: {}", id);
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        log.info("Fetching all books from the database");
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Integer id, Book bookDetails) {
        log.info("Updating book with id: {}", id);
        // Find the existing book or throw an exception if not found
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found for id: " + id));

        // Update the fields
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setPrice(bookDetails.getPrice());
        existingBook.setIsbn(bookDetails.getIsbn());
        existingBook.setStockQuantity(bookDetails.getStockQuantity());

        // Save the updated book back to the database
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Integer id) {
        log.info("Deleting book with id: {}", id);
        bookRepository.deleteById(id);
    }
}