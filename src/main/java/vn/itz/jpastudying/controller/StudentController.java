package vn.itz.jpastudying.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.exceptions.ApiException;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.service.StudentDaoService;

@RestController
public class StudentController {
  @Autowired
  private StudentDaoService studentDaoService;

  @GetMapping("/students")
  public ResponseEntity<ApiException<List<Student>>> getAllStudents(){
    ApiException<List<Student>> respone = studentDaoService.findAllStudents();
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }

  @GetMapping("/student/{id}")
  public ResponseEntity<ApiException<Student>> getStudentById(@PathVariable int id) {
    ApiException<Student> response = studentDaoService.findStudentById(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PostMapping("/student")
  public ResponseEntity<ApiException<Student>> createStudent(@Valid @RequestBody Student student) {
    ApiException<Student> response = studentDaoService.createStudent(student);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @DeleteMapping("/student/{id}")
  public ResponseEntity<ApiException<Student>> deleteStudent(@PathVariable int id) {
    ApiException<Student> response = studentDaoService.deleteStudent(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PutMapping("/student/{id}")
  public ResponseEntity<ApiException<Student>> updateStudent(@PathVariable int id, @Valid @RequestBody Student student) {
    ApiException<Student> response = studentDaoService.updateStudent(id, student);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}
