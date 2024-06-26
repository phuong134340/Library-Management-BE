package com.se104.librarymanagementbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryManagementBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementBeApplication.class, args);
    }

}
