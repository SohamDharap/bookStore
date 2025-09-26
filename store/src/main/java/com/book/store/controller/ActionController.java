package com.book.store.controller;
import com.book.store.beans.*;
import com.book.store.entity.Book;
import com.book.store.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore/")
public class ActionController {

    @Autowired
    ActionService actionService; 

    @PostMapping("/addBook")
    public ResponseEntity<AddBookResponse> addBook(@RequestBody AddBookRequest request) {
        AddBookResponse response = new AddBookResponse();
        try {
            Book book = actionService.addBook(request);
            response.setBook(book);
            response.setStatus("Success");
            response.setStatusCode(200);
            response.setStatusMessage("Book added successfully");
        } catch (Exception e) {
            response.setBook(null);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error adding book: " + e.getMessage());
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/getBook")
    public ResponseEntity<GetBooksResponse> getBook(@RequestBody Integer id) {
        GetBooksResponse response = new GetBooksResponse();
        try {
            Book book = actionService.getBookById(id);
            if (book != null) {
                response.setBook(book);
                response.setStatus("Success");
                response.setStatusCode(200);
                response.setStatusMessage("Book fetched successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setBook(null);
                response.setStatus("Failure");
                response.setStatusCode(404);
                response.setStatusMessage("Book not found");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.setBook(null);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error fetching book: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/updateBook")
    public ResponseEntity<UpdateBookResponse> updateBook(@RequestBody Book book) {
        UpdateBookResponse response = new UpdateBookResponse();
        try {
            Book updatedBook = actionService.updateBook(Integer.valueOf(book.getId()), book);
            response.setBook(updatedBook);
            response.setUpdated(updatedBook != null);
            response.setStatus("Success");
            response.setStatusCode(200);
            response.setStatusMessage("Book updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
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
        DeleteBookResponse response = new DeleteBookResponse();
        try {
            boolean deleted = actionService.deleteBook(id);
            if (deleted) {
                response.setStatus("Success");
                response.setStatusCode(200);
                response.setStatusMessage("Book deleted successfully");
            } else {
                response.setStatus("Failure");
                response.setStatusCode(404);
                response.setStatusMessage("Book not found");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error deleting book: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/getAllBooks")
    public ResponseEntity<GetAllBooksResponse> getAllBooks() {
        GetAllBooksResponse response = new GetAllBooksResponse();
        try {
            response.setBooks(actionService.getAllBooks());
            response.setStatus("Success");
            response.setStatusCode(200);
            response.setStatusMessage("Books fetched successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setBooks(null);
            response.setStatus("Failure");
            response.setStatusCode(500);
            response.setStatusMessage("Error fetching books: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}