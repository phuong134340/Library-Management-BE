package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.Reader_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderTypeRepository extends JpaRepository<Reader_Type, Long> {
}
