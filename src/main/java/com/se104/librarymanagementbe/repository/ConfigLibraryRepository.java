package com.se104.librarymanagementbe.repository;

import com.se104.librarymanagementbe.entity.ConfigLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigLibraryRepository extends JpaRepository<ConfigLibrary, Long> {

}
