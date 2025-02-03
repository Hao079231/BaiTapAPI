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
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.service.SubjectDaoService;

@RestController
public class SubjectController {
  @Autowired
  private SubjectDaoService subjectDaoService;

  // Lay tat ca danh sach cac khoa hoc
  @GetMapping("/subjects")
  public ResponseEntity<ApiException<List<Subject>>> getAllSubject(){
    ApiException<List<Subject>> respone = subjectDaoService.getAllSubject();
    return ResponseEntity.status(respone.getHttpStatus()).body(respone);
  }

  // Lay thong tin mot khoa hoc
  @GetMapping("/subject/{id}")
  public ResponseEntity<ApiException<Subject>> getSubjectById(@PathVariable int id) {
    ApiException<Subject> response = subjectDaoService.findSubjectById(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Them thong tin mot khoa hoc
  @PostMapping("/subject")
  public ResponseEntity<ApiException<Subject>> createSubject(@Valid @RequestBody Subject subject) {
    ApiException<Subject> response = subjectDaoService.createSubject(subject);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Xoa thong tin mot khoa hoc
  @DeleteMapping("/subject/{id}")
  public ResponseEntity<ApiException<Subject>> deleteSubject(@PathVariable int id) {
    ApiException<Subject> response = subjectDaoService.deleteSubject(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  // Cap nhat thong tin mot khoa hoc
  @PutMapping("/subject/{id}")
  public ResponseEntity<ApiException<Subject>> updateSubject(@PathVariable int id, @Valid @RequestBody Subject subject) {
    ApiException<Subject> response = subjectDaoService.updateSubject(id, subject);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}
