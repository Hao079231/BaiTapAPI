package vn.itz.jpastudying.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.Dto.SubjectRegistrationDto;
import vn.itz.jpastudying.Dto.SubjectDto;
import vn.itz.jpastudying.form.subject.SubjectCreateForm;
import vn.itz.jpastudying.form.subject.SubjectUpdateForm;
import vn.itz.jpastudying.model.criteria.SubjectCriteria;
import vn.itz.jpastudying.model.criteria.SubjectRegistrationCriteria;
import vn.itz.jpastudying.service.SubjectDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/subject")
public class SubjectController {
  @Autowired
  private SubjectDaoService subjectDaoService;

  // Lay tat ca danh sach cac khoa hoc
  @GetMapping("/list")
  public ResponseEntity<ApiMessageDto<List<SubjectDto>>> getAllSubject(){
    ApiMessageDto<List<SubjectDto>> respone = ApiResponeUtils.results("Danh sach cac khoa hoc",
        subjectDaoService.getAllSubject());
    return ResponseEntity.ok(respone);
  }

  // Lay thong tin mot khoa hoc
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<SubjectDto>> getSubjectById(@PathVariable int id) {
    ApiMessageDto<SubjectDto> response = ApiResponeUtils.results("Thong tin mot khoa hoc",
        subjectDaoService.findSubjectById(id));
    return ResponseEntity.ok(response);
  }

  // Them thong tin mot khoa hoc
  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<SubjectDto>> createSubject(@Valid @RequestBody SubjectCreateForm subject) {
    ApiMessageDto<SubjectDto> response = ApiResponeUtils.results("Them thong tin khoa hoc thanh cong",
        subjectDaoService.createSubject(subject));
    return ResponseEntity.ok(response);
  }

  // Xoa thong tin mot khoa hoc
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAuthority('C_DEL')")
  public ResponseEntity<ApiMessageDto<Void>> deleteSubject(@PathVariable int id) {
    subjectDaoService.deleteSubject(id);
    ApiMessageDto<Void> response = ApiResponeUtils.results("Xoa thong tin khoa hoc thanh cong",
        null);
    return ResponseEntity.ok(response);
  }

  // Cap nhat thong tin mot khoa hoc
  @PutMapping("/update/{id}")
  @PreAuthorize("hasAuthority('C_UPD')")
  public ResponseEntity<ApiMessageDto<SubjectDto>> updateSubject(@PathVariable int id, @Valid @RequestBody SubjectUpdateForm subject) {
    ApiMessageDto<SubjectDto> response = ApiResponeUtils.results("Cap nhat thong tin khoa hoc thanh cong",
        subjectDaoService.updateSubject(id, subject));
    return ResponseEntity.ok(response);
  }

  // Loc va phan trang khoa hoc
  @GetMapping("/pagination")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<SubjectDto>>> getPagedSubjects(
      SubjectCriteria subjectCriteria, Pageable pageable) {

    ShowPagedResults<SubjectDto> subjects = subjectDaoService.getFilteredSubjects(subjectCriteria, pageable);
    ApiMessageDto<ShowPagedResults<SubjectDto>> response = ApiResponeUtils.results("Danh sach khoa hoc", subjects);

    return ResponseEntity.ok(response);
  }

  // Loc va phan trang danh sach khoa hoc tu id sinh vien va ngay nhap vao
  @GetMapping("/list-by-student-and-date")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<SubjectRegistrationDto>>> getSubjectsByStudentIdAndDate(
      SubjectRegistrationCriteria criteria, Pageable pageable) {

    ShowPagedResults<SubjectRegistrationDto> subjects = subjectDaoService.getSubjectsByCriteria(criteria, pageable);
    ApiMessageDto<ShowPagedResults<SubjectRegistrationDto>> response = ApiResponeUtils.results("Danh sach khoa hoc duoc mot sinh vien dang ky", subjects);

    return ResponseEntity.ok(response);
  }


}
