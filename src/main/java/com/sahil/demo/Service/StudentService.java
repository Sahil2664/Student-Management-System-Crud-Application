package com.sahil.demo.Service;

import com.sahil.demo.DTO.CreateStudentRequestDTO;
import com.sahil.demo.DTO.CreateStudentResponseDTO;
import com.sahil.demo.DTO.UpdateStudentRequestDTO;
import com.sahil.demo.DTO.UpdateStudentResponseDTO;
import com.sahil.demo.Entity.Student;
import com.sahil.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO createStudentRequestDTO) {
        Student student = new Student();
        student.setName(createStudentRequestDTO.getName());
        student.setAge(createStudentRequestDTO.getAge());
        student.setDepartment(createStudentRequestDTO.getDepartment());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        studentRepository.save(student);

        return mapToCreateResponseDTO(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public UpdateStudentResponseDTO updateStudent(int id, UpdateStudentRequestDTO updateStudentRequestDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(updateStudentRequestDTO.getName());
        student.setAge(updateStudentRequestDTO.getAge());
        student.setDepartment(updateStudentRequestDTO.getDepartment());
        student.setUpdatedAt(LocalDateTime.now());

        studentRepository.save(student);

        return mapToUpdateResponseDTO(student);
    }

    public Student deleteStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        
        studentRepository.delete(student);
        return student;
    }

    private CreateStudentResponseDTO mapToCreateResponseDTO(Student student) {
        CreateStudentResponseDTO dto = new CreateStudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setDepartment(student.getDepartment());
        return dto;
    }

    private UpdateStudentResponseDTO mapToUpdateResponseDTO(Student student) {
        UpdateStudentResponseDTO dto = new UpdateStudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setDepartment(student.getDepartment());
        return dto;
    }
}
