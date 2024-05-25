package com.se104.librarymanagementbe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name ="reader_type" )
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reader_Type {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "readerType_seq")
    @SequenceGenerator(
            name = "readerType_seq",
            sequenceName = "readerType_seq",
            allocationSize = 1
    )
    @Id
    private long id;
    @Column(name = "name")
    private String name;
}
