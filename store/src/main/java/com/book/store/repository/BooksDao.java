package com.book.store.repository;

import com.book.store.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BooksDao extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    Optional<Book> findByIsbn(String isbn);
}