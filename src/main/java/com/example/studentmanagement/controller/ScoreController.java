package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.ScoreDTO;
import com.example.studentmanagement.dto.StudentScoreDTO;
import com.example.studentmanagement.entity.Score;
import com.example.studentmanagement.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService studentScoreService;

    @PostMapping("/add")
    public ResponseEntity<ScoreDTO> addScore(@RequestBody ScoreDTO studentScoreDto) {
        ScoreDTO studentScore = studentScoreService.addScore(studentScoreDto);
        return ResponseEntity.ok(studentScore);
    }
    @GetMapping
    public ResponseEntity<List<StudentScoreDTO>> getAllStudentScores() {
        List<StudentScoreDTO> scores = studentScoreService.getAllStudentScores();
        return ResponseEntity.ok(scores);
    }
}
