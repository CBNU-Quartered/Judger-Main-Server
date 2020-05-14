package com.qt.student;

import com.qt.domain.student.dto.StudentInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<StudentInfo> findAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentInfo.class))
                .collect(Collectors.toList());
    }
}
