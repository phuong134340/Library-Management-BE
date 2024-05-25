package com.se104.librarymanagementbe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "fine")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fine {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fine_seq")
    @SequenceGenerator(
            name = "fine_seq",
            sequenceName = "fine_seq",
            allocationSize = 1
    )
    @Id
    private long id;
    @Column(name = "reader_name")
    private String name;
    @Column(name = "total")
    private long total;
    @Column(name = "money")
    private long money;
    @Column(name = "change")
    private long change;
    @OneToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    private Reader reader;
}
