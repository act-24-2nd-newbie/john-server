package com.sds.todolist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity(name = "member")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false, length = 50)
    private String userName;
    @Column(nullable = false)
    private Instant signupDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    @PrePersist
    void beforeCreate() {
        signupDate = Instant.now();
    }
}
