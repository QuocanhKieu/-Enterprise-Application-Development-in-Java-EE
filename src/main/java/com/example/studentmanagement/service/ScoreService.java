package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.ScoreDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Score;
import com.example.studentmanagement.entity.Subject;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.repository.ScoreRepository;
import com.example.studentmanagement.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public ScoreDTO addScore(ScoreDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Score score = Score.builder()
                .student(student)
                .subject(subject)
                .score1(dto.getScore1())
                .score2(dto.getScore2())
                .build();

        scoreRepository.save(score);
        double finalGrade = score.calculateGrade();
        return ScoreDTO.builder()
                .studentId(student.getId())
                .subjectId(subject.getId())
                .score1(dto.getScore1())
                .score2(dto.getScore2())
                .grade(gradeToLetter(finalGrade))
                .build();
    }

    private String gradeToLetter(double grade) {
        if (grade >= 90) return "A";
        if (grade >= 80) return "B";
        if (grade >= 70) return "C";
        if (grade >= 60) return "D";
        return "F";
    }
}
