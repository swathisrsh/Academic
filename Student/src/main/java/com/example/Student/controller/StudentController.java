package com.example.Student.controller;

import com.example.Student.entity.Student;
import com.example.Student.exception.RecordNotFoundException;
import com.example.Student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentRepository repository;

    @PostMapping("/add")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student stu = repository.save(student);
        return ResponseEntity.ok(stu);
    }

    @GetMapping("/getAll")
    public List<Student> getAll(){
        return repository.findAll();
    }

   @GetMapping("{id}")
   public Student getStudentById(@PathVariable Long id){
    Optional<Student> student = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException()));
    return student.get();
    }

    @DeleteMapping("{id}")
    public String deleteStudentById(@PathVariable Long id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return "student record deleted successfully";
        } else {
            throw new RecordNotFoundException("No Record exists with the id"+id);
        }

    }
}
