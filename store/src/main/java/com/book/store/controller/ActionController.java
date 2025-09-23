package com.book.store.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/bookstore/")
public class ActionController {

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book) {
        return ResponseEntity.ok("Book added successfully");
    }
}