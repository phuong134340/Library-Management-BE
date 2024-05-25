package com.se104.librarymanagementbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Entity
@Table (name = "configLibrary")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigLibrary {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "configLibrary_seq")
    @SequenceGenerator(
            name = "configLibrary_seq",
            sequenceName = "configLibrary_seq",
            allocationSize = 1
    )
    @Id
    private long id;
    @Column(name = "age_max")
    private long ageMax;
    @Column(name = "age_min")
    private long ageMin;
    @Column(name = "card_validity")
    private long cardValidity;
    @Column(name = "year_of_publication")
    private long yearOfPublication;
    @Column(name = "limit_book")
    private long limitBook;
    @Column(name = "day_max")
    private long dayMax;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
}
