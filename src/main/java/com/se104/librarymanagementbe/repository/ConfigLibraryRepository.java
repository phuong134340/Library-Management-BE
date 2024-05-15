package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.ConfigLibrary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigLibraryRepository extends JpaRepository<ConfigLibrary, Long> {
    //Page<ConfigLibrary> findAllByOrderByCreatedAtDesc(Pageable pageable);
    List<ConfigLibrary> findAllByOrderByCreatedAtDesc();
}
