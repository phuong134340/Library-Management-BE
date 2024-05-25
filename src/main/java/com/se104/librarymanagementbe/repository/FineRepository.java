package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FineRepository extends JpaRepository<Fine, Long> {
    Optional<Fine> findOneByReaderId(long readerId);

}
