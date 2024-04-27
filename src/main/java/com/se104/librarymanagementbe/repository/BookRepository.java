package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
