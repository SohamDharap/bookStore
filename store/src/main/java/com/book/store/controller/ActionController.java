package com.book.store.controller;

import com.book.store.beans.*;
import com.book.store.entity.Book;
import com.book.store.service.ActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore") // Removed trailing slash for convention
public class ActionController {

    @Autowired
    private ActionService actionService;

    private static final Logger log = LoggerFactory.getLogger(ActionController.class);

    @PostMapping("/addBook")
    public ResponseEntity<AddBookResponse> addBook(@RequestBody AddBookRequest request) {
        log.info("Request received to add a new book with title: '{}'", request.getTitle());
        AddBookResponse response = new AddBookResponse();
        try {
            // Logic to create a Book entity from the request
            Book newBook = new Book();
            newBook.setTitle(request.getTitle());
            newBook.setAuthor(request.getAuthor());
            newBook.setIsbn(request.getIsbn());
            newBook.setPrice(request.getPrice());
            newBook.setStockQuantity(request.getStockQuantity());

            Book savedBook = actionService.addBook(newBook);

            response.setBook(savedBook);
            response.setStatus("Success");
            response.setStatusCode(200);
            response.setStatusMessage("Book added successfully");
            log.info("Successfully added book with ID: {}", savedBook.getId());

        } catch (Exception e) {
            log.error("Error occurred while adding book: {}", request.getTitle(), e);
            response.setBook(null);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error adding book: " + e.getMessage());
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/getBook")
    public ResponseEntity<GetBooksResponse> getBook(@RequestBody GetBookRequest request) {
        log.info("Request received to fetch book with ID: {}", request.getId());
        GetBooksResponse response = new GetBooksResponse();
        try {
            Optional<Book> bookOptional = actionService.getBookById(request.getId());
            if (bookOptional.isPresent()) {
                response.setBook(bookOptional.get());
                response.setStatus("Success");
                response.setStatusCode(200);
                response.setStatusMessage("Book fetched successfully");
                log.info("Successfully fetched book with ID: {}", request.getId());
                return ResponseEntity.ok(response);
            } else {
                response.setBook(null);
                response.setStatus("Failure");
                response.setStatusCode(404);
                response.setStatusMessage("Book not found");
                log.warn("No book found for ID: {}", request.getId()); // Use WARN for expected "not found" cases
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching book with ID: {}", request.getId(), e);
            response.setBook(null);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error fetching book: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/updateBook")
    public ResponseEntity<UpdateBookResponse> updateBook(@RequestBody Book book) {
        log.info("Request received to update book with ID: {}", book.getId());
        UpdateBookResponse response = new UpdateBookResponse();
        try {
            Book updatedBook = actionService.updateBook(book.getId(), book);
            response.setBook(updatedBook);
            response.setUpdated(true);
            response.setStatus("Success");
            response.setStatusCode(200);
            response.setStatusMessage("Book updated successfully");
            log.info("Successfully updated book with ID: {}", book.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while updating book with ID: {}", book.getId(), e);
            response.setBook(null);
            response.setUpdated(false);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error updating book: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<DeleteBookResponse> deleteBook(@RequestBody Integer id) {
        log.info("Request received to delete book with ID: {}", id);
        DeleteBookResponse response = new DeleteBookResponse();
        try {
            actionService.deleteBook(id);
            response.setStatus("Success");
            response.setStatusCode(200);
            response.setStatusMessage("Book deleted successfully");
            log.info("Successfully deleted book with ID: {}", id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while deleting book with ID: {}", id, e);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error deleting book: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/getAllBooks") // Changed to GET as it's more appropriate
    public ResponseEntity<GetAllBooksResponse> getAllBooks() {
        log.info("Request received to fetch all books");
        GetAllBooksResponse response = new GetAllBooksResponse();
        try {
            List<Book> books = actionService.getAllBooks();
            response.setBooks(books);

            response.setStatus("Success");
            response.setStatusCode(200);
            response.setStatusMessage("Books fetched successfully");
            log.info("Successfully fetched {} books", books.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while fetching all books", e);
            response.setBooks(null);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error fetching books: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}