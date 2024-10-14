package com.sds.todolist.domain;

import com.sds.todolist.dto.TaskResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String contents;
    @Column(nullable = false)
    private Boolean isDone;
    @Column(nullable = false)
    private Instant modifiedDate;
    @Column(nullable = false)
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @PrePersist
    void beforeCreate() {
        this.isDone = false;
        this.modifiedDate = Instant.now();
        this.createdDate = Instant.now();
    }

    @PreUpdate
    void beforeUpdate() {
        this.modifiedDate = Instant.now();
    }

    public TaskResponseDto toResponse() {
        return TaskResponseDto.builder().id(this.id).contents(this.contents).isDone(this.isDone).modifiedDate(this.modifiedDate).createdDate(this.createdDate).build();
    }
}
