package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
