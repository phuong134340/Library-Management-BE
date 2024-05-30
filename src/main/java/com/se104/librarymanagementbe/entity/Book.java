package com.se104.librarymanagementbe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq")
    @SequenceGenerator(
            name = "books_seq",
            sequenceName = "books_seq",
            allocationSize = 1
    )
    @Id
    private long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @Column(name = "publish_date")
    private Instant publishDate;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "price")
    private long price;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "description")
    private String description;
//    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
//    private List<Loan> loans;
}
