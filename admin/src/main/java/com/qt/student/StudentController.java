package com.qt.student;

import com.qt.domain.Student;
import com.qt.student.dto.StudentInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentInfo>> findAllStudents() {
        List<StudentInfo> studentInfos = studentService.findAllStudents();
        return ResponseEntity.ok(studentInfos);
    }
}
