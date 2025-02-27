package com.example.studentmanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoreDTO {
    private Integer studentId;
    private Integer subjectId;
    private Double score1;
    private Double score2;
    private String grade;
}
