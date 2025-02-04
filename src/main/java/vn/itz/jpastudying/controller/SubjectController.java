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
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.service.SubjectDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
public class SubjectController {
  @Autowired
  private SubjectDaoService subjectDaoService;

  // Lay tat ca danh sach cac khoa hoc
  @GetMapping("/subjects")
  public ResponseEntity<ApiMessageDto<List<Subject>>> getAllSubject(){
    ApiMessageDto<List<Subject>> respone = ApiResponeUtils.results("Danh sach cac khoa hoc",
        subjectDaoService.getAllSubject());
    return ResponseEntity.ok(respone);
  }

  // Lay thong tin mot khoa hoc
  @GetMapping("/subject/{id}")
  public ResponseEntity<ApiMessageDto<Subject>> getSubjectById(@PathVariable int id) {
    ApiMessageDto<Subject> response = ApiResponeUtils.results("Thong tin mot khoa hoc",
        subjectDaoService.findSubjectById(id));
    return ResponseEntity.ok(response);
  }

  // Them thong tin mot khoa hoc
  @PostMapping("/subject")
  public ResponseEntity<ApiMessageDto<Subject>> createSubject(@Valid @RequestBody Subject subject) {
    ApiMessageDto<Subject> response = ApiResponeUtils.results("Them thong tin khoa hoc thanh cong",
        subjectDaoService.createSubject(subject));
    return ResponseEntity.ok(response);
  }

  // Xoa thong tin mot khoa hoc
  @DeleteMapping("/subject/{id}")
  public ResponseEntity<ApiMessageDto<Void>> deleteSubject(@PathVariable int id) {
    subjectDaoService.deleteSubject(id);
    ApiMessageDto<Void> response = ApiResponeUtils.results("Xoa thong tin khoa hoc thanh cong",
        null);
    return ResponseEntity.ok(response);
  }

  // Cap nhat thong tin mot khoa hoc
  @PutMapping("/subject/{id}")
  public ResponseEntity<ApiMessageDto<Subject>> updateSubject(@PathVariable int id, @Valid @RequestBody Subject subject) {
    ApiMessageDto<Subject> response = ApiResponeUtils.results("Cap nhat thong tin khoa hoc thanh cong",
        subjectDaoService.updateSubject(id, subject));
    return ResponseEntity.ok(response);
  }

  // Dang ky mot khoa hoc cho sinh vien

}
