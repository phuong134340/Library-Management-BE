package com.se104.librarymanagementbe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "return_book")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Return_Book {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(name = "reader_name")
    private String name;
    @Column(name = "return_date")
    @CreatedDate
    private Instant returnDate;
    @Column(name = "fine")
    private long fine;
    @Column(name = "number_of_day")
    private long number;
//    @OneToMany(mappedBy = "return_book")
//    private List<Loan> loans;
}
