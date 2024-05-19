package com.se104.librarymanagementbe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "readers")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String id;
    @Column(name = "reader_name")
    private String name;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "type")
    private String readerType;
    @Column(name = "date_of_birth")
    private Instant dateOfBirth;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "status")
    private String status;
    @Column(name = "expired_date")
    private Instant expiredDate;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
    @OneToMany(mappedBy = "reader")
    private List<Loan> loans;
}
