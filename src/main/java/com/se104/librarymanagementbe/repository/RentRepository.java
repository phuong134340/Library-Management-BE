package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findAllByReaderIdAndStatusIs(long readerId, String status);
}
