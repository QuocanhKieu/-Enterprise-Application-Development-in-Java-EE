package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = Student.builder()
                .studentCode(studentDTO.getStudentCode())
                .fullName(studentDTO.getFullName())
                .address(studentDTO.getAddress())
                .build();
        Student savedStudent = studentRepository.save(student);
        return StudentDTO.builder()
                .id(savedStudent.getId())
                .studentCode(savedStudent.getStudentCode())
                .fullName(savedStudent.getFullName())
                .address(savedStudent.getAddress())
                .build();
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .studentCode(student.getStudentCode())
                        .fullName(student.getFullName())
                        .address(student.getAddress())
                        .build())
                .collect(Collectors.toList());
    }
}
