package com.sahil.demo.Controller;

import com.sahil.demo.DTO.CreateStudentRequestDTO;
import com.sahil.demo.DTO.CreateStudentResponseDTO;
import com.sahil.demo.DTO.UpdateStudentRequestDTO;
import com.sahil.demo.DTO.UpdateStudentResponseDTO;
import com.sahil.demo.Entity.Student;
import com.sahil.demo.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> storeStudent(@Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO) {
        CreateStudentResponseDTO result = studentService.createStudent(createStudentRequestDTO);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody UpdateStudentRequestDTO updateStudentRequestDTO) {
        
        UpdateStudentResponseDTO result = studentService.updateStudent(id, updateStudentRequestDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body("Student deleted successfully");
    }
}
