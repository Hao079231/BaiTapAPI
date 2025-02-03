package vn.itz.jpastudying.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.exceptions.ApiException;
import vn.itz.jpastudying.model.StudentSubject;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.service.StudentDaoService;

@RestController
public class StudentSubjectController {
  @Autowired
  private StudentDaoService studentDaoService;
  // Lay tat cac danh sach sinh vien dang ky khoa hoc
  @GetMapping("/register")
  public ResponseEntity<ApiException<List<StudentSubject>>> getAllRegistation(){
    ApiException<List<StudentSubject>> respone = studentDaoService.getAllRegistration();
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }

  // Lay danh sach khoa hoc ma mot sinh vien dang ky
  @GetMapping("/register/{student_id}")
  public ResponseEntity<ApiException<List<Subject>>> getSubjectsByStudentId(@PathVariable int student_id){
    ApiException<List<Subject>> response = studentDaoService.getSubjectsByStudenId(student_id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Dang ky khoa hoc cho mot sinh vien
  @PostMapping("register/{studentId}/{subjectId}")
  public ResponseEntity<ApiException<StudentSubject>> registerForCourses(@PathVariable int studentId, @PathVariable int subjectId){
    ApiException<StudentSubject> respone = studentDaoService.registerForCourses(studentId, subjectId);
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }

  // Xoa khoa hoc danh cho mot sinh vien
  @DeleteMapping("register/{studentId}/{subjectId}")
  public ResponseEntity<ApiException<StudentSubject>> deleteForCourses(@PathVariable int studentId, @PathVariable int subjectId){
    ApiException<StudentSubject> respone = studentDaoService.deleteForCourses(studentId, subjectId);
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }
}
