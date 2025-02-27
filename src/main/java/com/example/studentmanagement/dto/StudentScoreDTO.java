package com.example.studentmanagement.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentScoreDTO{
    private Integer studentId;
    private String studentName;
    private String subjectName;
    private Double score1;
    private Double score2;
    private Integer credit;
    private BigDecimal grade;
    private String letterGrade;  // Add this field
}
