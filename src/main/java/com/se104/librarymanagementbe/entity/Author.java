package com.se104.librarymanagementbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "authors")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private long age;
    @Column(name = "address")
    private String address;
    @Column(name = "number_of_books")
    private long number;
//    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
//    private List<Book> books;
}
