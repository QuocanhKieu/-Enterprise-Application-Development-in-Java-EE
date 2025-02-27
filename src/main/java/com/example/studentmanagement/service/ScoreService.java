package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.ScoreDTO;
import com.example.studentmanagement.dto.StudentScoreDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Score;
import com.example.studentmanagement.entity.Subject;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.repository.ScoreRepository;
import com.example.studentmanagement.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        if (grade >= 8) return "A";
        if (grade >= 6) return "B";
        if (grade >= 4) return "D";
        return "F";
    }


    public List<StudentScoreDTO> getAllStudentScores() {
        List<Score> studentScores = scoreRepository.findAll();

        return studentScores.stream().map(score -> {
            BigDecimal grade = (BigDecimal.valueOf(score.getScore1()).multiply(new BigDecimal("0.3")))
                    .add(BigDecimal.valueOf(score.getScore2()).multiply(new BigDecimal("0.7")))
                    .setScale(2, RoundingMode.HALF_UP);

            return StudentScoreDTO.builder()
                    .studentId(score.getStudent().getId())
                    .studentName(score.getStudent().getFullName())
                    .subjectName(score.getSubject().getSubjectName())
                    .score1(score.getScore1())
                    .score2(score.getScore2())
                    .credit(score.getSubject().getCredit())
                    .grade(grade)
                    .letterGrade(gradeToLetter(grade.doubleValue())) // Convert to letter grade
                    .build();
        }).collect(Collectors.toList());
    }

}
