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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "readers_seq")
    @SequenceGenerator(
            name = "readers_seq",
            sequenceName = "readers_seq",
            allocationSize = 1
    )
    @Id
    private long id;
    @Column(name = "reader_name")
    private String name;
    @Column(name = "fullname")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "reader_type_id")
    private Reader_Type readerType;
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
//    @OneToMany(mappedBy = "reader",fetch = FetchType.LAZY)
//    private List<Loan> loans;
}
