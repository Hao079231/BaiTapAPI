package vn.itz.jpastudying.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.Dto.request.SubjectCreateRequestDto;
import vn.itz.jpastudying.Dto.request.SubjectUpdateRequestDto;
import vn.itz.jpastudying.Dto.response.StudentResponseDto;
import vn.itz.jpastudying.Dto.response.SubjectResponseDto;
import vn.itz.jpastudying.model.StudentCriteria;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.model.SubjectCriteria;
import vn.itz.jpastudying.service.SubjectDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
public class SubjectController {
  @Autowired
  private SubjectDaoService subjectDaoService;

  // Lay tat ca danh sach cac khoa hoc
  @GetMapping("/subjects")
  public ResponseEntity<ApiMessageDto<List<SubjectResponseDto>>> getAllSubject(){
    ApiMessageDto<List<SubjectResponseDto>> respone = ApiResponeUtils.results("Danh sach cac khoa hoc",
        subjectDaoService.getAllSubject());
    return ResponseEntity.ok(respone);
  }

  // Lay thong tin mot khoa hoc
  @GetMapping("/subject/{id}")
  public ResponseEntity<ApiMessageDto<SubjectResponseDto>> getSubjectById(@PathVariable int id) {
    ApiMessageDto<SubjectResponseDto> response = ApiResponeUtils.results("Thong tin mot khoa hoc",
        subjectDaoService.findSubjectById(id));
    return ResponseEntity.ok(response);
  }

  // Them thong tin mot khoa hoc
  @PostMapping("/subject")
  public ResponseEntity<ApiMessageDto<SubjectResponseDto>> createSubject(@Valid @RequestBody SubjectCreateRequestDto subject) {
    ApiMessageDto<SubjectResponseDto> response = ApiResponeUtils.results("Them thong tin khoa hoc thanh cong",
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
  public ResponseEntity<ApiMessageDto<SubjectResponseDto>> updateSubject(@PathVariable int id, @Valid @RequestBody SubjectUpdateRequestDto subject) {
    ApiMessageDto<SubjectResponseDto> response = ApiResponeUtils.results("Cap nhat thong tin khoa hoc thanh cong",
        subjectDaoService.updateSubject(id, subject));
    return ResponseEntity.ok(response);
  }

  // Loc va phan trang khoa hoc
  @GetMapping("/subject/pagination")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<SubjectResponseDto>>> getPagedSubjects(
      SubjectCriteria subjectCriteria, Pageable pageable) {

    ShowPagedResults<SubjectResponseDto> subjects = subjectDaoService.getFilteredSubjects(subjectCriteria, pageable);
    ApiMessageDto<ShowPagedResults<SubjectResponseDto>> response = ApiResponeUtils.results("Danh sach khoa hoc", subjects);

    return ResponseEntity.ok(response);
  }

}
