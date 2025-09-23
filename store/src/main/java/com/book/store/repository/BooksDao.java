package com.book.store.repository;

import com.book.store.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BooksDao extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    Optional<Book> findByIsbn(String isbn);
}