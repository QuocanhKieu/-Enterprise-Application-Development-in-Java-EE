package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_score_t")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "score1", nullable = false)
    private Double score1;

    @Column(name = "score2", nullable = false)
    private Double score2;

    public double calculateGrade() {
        return (0.3 * score1) + (0.7 * score2);
    }
}
