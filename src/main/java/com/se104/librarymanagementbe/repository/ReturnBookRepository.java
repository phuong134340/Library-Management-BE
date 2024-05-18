package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.Return_Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnBookRepository extends JpaRepository<Return_Book, Long> {
}
