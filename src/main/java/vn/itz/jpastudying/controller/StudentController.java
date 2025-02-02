package vn.itz.jpastudying.controller;

import java.util.List;
import java.util.Optional;
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
import vn.itz.jpastudying.model.StudentSubject;
import vn.itz.jpastudying.service.StudentDaoService;

@RestController
public class StudentController {
  @Autowired
  private StudentDaoService studentDaoService;

  // Lay tat ca du lieu trong bang sinh vien
  @GetMapping("/students")
  public ResponseEntity<ApiException<List<Student>>> getAllStudents(){
    ApiException<List<Student>> respone = studentDaoService.findAllStudents();
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }

  // Lay ra du lieu cua mot sinh vien
  @GetMapping("/student/{id}")
  public ResponseEntity<ApiException<Student>> getStudentById(@PathVariable int id) {
    ApiException<Student> response = studentDaoService.findStudentById(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Them mot sinh vien vao bang
  @PostMapping("/student")
  public ResponseEntity<ApiException<Student>> createStudent(@Valid @RequestBody Student student) {
    ApiException<Student> response = studentDaoService.createStudent(student);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Xoa mot sinh vien ra khoi bang
  @DeleteMapping("/student/{id}")
  public ResponseEntity<ApiException<Student>> deleteStudent(@PathVariable int id) {
    ApiException<Student> response = studentDaoService.deleteStudent(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Cap nhat du lieu sinh vien trong bang
  @PutMapping("/student/{id}")
  public ResponseEntity<ApiException<Student>> updateStudent(@PathVariable int id, @Valid @RequestBody Student student) {
    ApiException<Student> response = studentDaoService.updateStudent(id, student);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Lay tat cac khoa hoc ma sinh vien dang ky
  @GetMapping("/student/{studentId}/register")
  public ResponseEntity<ApiException<Optional<StudentSubject>>> getAllRegistation(@PathVariable int studentId){
    ApiException<Optional<StudentSubject>> respone = studentDaoService.getAllRegistration(studentId);
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }

  // Dang ky khoa hoc cho mot sinh vien
  @PostMapping("student/{studentId}/register/{subjectId}")
  public ResponseEntity<ApiException<StudentSubject>> registerForCourses(@PathVariable int studentId, @PathVariable int subjectId){
    ApiException<StudentSubject> respone = studentDaoService.registerForCourses(studentId, subjectId);
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }
}
