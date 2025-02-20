package vn.itz.jpastudying.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.Dto.StudentDto;
import vn.itz.jpastudying.Dto.StudyReportDto;
import vn.itz.jpastudying.Dto.SubjectRegistrationDto;
import vn.itz.jpastudying.form.student.StudentCreateForm;
import vn.itz.jpastudying.form.student.StudentUpdateForm;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.SubjectRegistration.Status;
import vn.itz.jpastudying.model.criteria.StudentCriteria;
import vn.itz.jpastudying.model.criteria.SubjectRegistrationCriteria;
import vn.itz.jpastudying.repository.StudentDAO;
import vn.itz.jpastudying.service.StudentDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/student")
public class StudentController {
  @Autowired
  private StudentDaoService studentDaoService;

  @Autowired
  private StudentDAO studentDAO;

//   Lay ra du lieu cua mot sinh vien
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<StudentDto>> getStudentById(@PathVariable int id) {

    ApiMessageDto<StudentDto> response = ApiResponeUtils.results("Thong tin sinh vien",
        studentDaoService.findStudentById(id));
    return ResponseEntity.ok(response);
  }

  // Them mot sinh vien vao bang
  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<StudentDto>> createStudent(@Valid @RequestBody StudentCreateForm student) {
    ApiMessageDto<StudentDto> response = ApiResponeUtils.results("Them du lieu sinh vien thanh cong",
        studentDaoService.createStudent(student));
    return ResponseEntity.ok(response);
  }

  // Xoa mot sinh vien ra khoi bang
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAuthority('C_DEL')")
  public ResponseEntity<ApiMessageDto<Void>> deleteStudent(@PathVariable int id) {
    studentDaoService.deleteStudent(id);
    ApiMessageDto<Void> respone = ApiResponeUtils.results("Xoa du lieu sinh vien thanh cong",
        null);
    return ResponseEntity.ok(respone);
  }

  // Cap nhat du lieu sinh vien trong bang
  @PutMapping("/update/{id}")
  @PreAuthorize("hasAuthority('C_UPD')")
  public ResponseEntity<ApiMessageDto<StudentDto>> updateStudent(@PathVariable int id, @Valid @RequestBody StudentUpdateForm student) {
    ApiMessageDto<StudentDto> response = ApiResponeUtils.results("Cap nhat du lieu sinh vien thanh cong",
        studentDaoService.updateStudent(id, student));
    return ResponseEntity.ok(response);
  }

  // Lay tat ca danh sach khoa hoc ma mot sinh vien dang ky - ManyToOne, OneToMany
  @GetMapping("/{student_id}/subjects")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<List<String>>> getEnrolledSubjects(@PathVariable int student_id) {
    List<String> subjectIds = studentDaoService.getEnrolledSubjects(student_id);
    ApiMessageDto<List<String>> response = ApiResponeUtils.results("Danh sach mon hoc ma sinh vien da dang ky", subjectIds);
    return ResponseEntity.ok(response);
  }

  // Dang ky khoa hoc cho sinh vien
  @PostMapping("/{student_id}/subject/{subject_id}")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<String>> enrollSubject(@PathVariable int student_id, @PathVariable int subject_id){
    ApiMessageDto<String> respone = ApiResponeUtils.results("Dang ky khoa hoc thanh cong",
        studentDaoService.enrollSubject(student_id, subject_id));
    return ResponseEntity.ok(respone);
  }

  // Xoa mot khoa hoc da dang ky
  @DeleteMapping("/{student_id}/subject/{subject_id}")
  @PreAuthorize("hasAuthority('C_DEL')")
  public ResponseEntity<ApiMessageDto<Student>> removeSubject(@PathVariable int student_id, @PathVariable int subject_id){
    ApiMessageDto<Student> respone = ApiResponeUtils.results("Xoa khoa hoc thanh cong",
        studentDaoService.removeSubject(student_id, subject_id));
    return ResponseEntity.ok(respone);
  }

  // Loc va phan trang sinh vien
  @GetMapping("/pagination")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<StudentDto>>> getPagedStudents(
      StudentCriteria studentCriteria, Pageable pageable) {

    ShowPagedResults<StudentDto> students = studentDaoService.getFilteredStudents(studentCriteria, pageable);
    ApiMessageDto<ShowPagedResults<StudentDto>> response = ApiResponeUtils.results("Danh sach sinh vien", students);

    return ResponseEntity.ok(response);
  }

  // Loc va phan trang danh sach sinh vien tu id khoa hoc va ngay nhap vao
  @GetMapping("/list-by-subject-and-date")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<SubjectRegistrationDto>>> getStudentsBySubjectIdAndDate(
      SubjectRegistrationCriteria criteria, Pageable pageable) {

    ShowPagedResults<SubjectRegistrationDto> students = studentDaoService.getStudentsByCriteria(criteria, pageable);
    ApiMessageDto<ShowPagedResults<SubjectRegistrationDto>> response = ApiResponeUtils.results(
        "Danh sach sinh vien da dang ky khoa hoc", students);

    return ResponseEntity.ok(response);
  }

  // Cap nhat trang thai sinh vien trong khoa hoc dang ky
  @PutMapping("/{studentId}/subject-registration/{subjectId}/status")
  @PreAuthorize("hasAuthority('C_UPD')")
  public ResponseEntity<ApiMessageDto<SubjectRegistrationDto>> updateRegistrationStatus(
      @PathVariable int studentId,
      @PathVariable int subjectId,
      @RequestParam("statusResponse") Status statusResponse) {

    SubjectRegistrationDto updatedStatus = studentDaoService.updateSubjectRegistrationStatus(studentId, subjectId, statusResponse);
    ApiMessageDto<SubjectRegistrationDto> response = ApiResponeUtils.results(
        "Cap nhat trang thai sinh vien trong khoa hoc thanh cong", updatedStatus);

    return ResponseEntity.ok(response);
  }

  // Thuc hien xoa mot sinh vien khong dung JPA
  @DeleteMapping("/statement/{id}")
  @PreAuthorize("hasAuthority('C_DEL')")
  public ResponseEntity<ApiMessageDto<Boolean>> deleteStudentStatement(@PathVariable int id) {
    boolean isDeleted = studentDAO.deleteStudentById(id);
    ApiMessageDto<Boolean> response = ApiResponeUtils.results("Xoa du lieu sinh vien thanh cong", isDeleted);
    return ResponseEntity.ok(response);
  }

  // Cap nhat diem cho sinh vien
  @PutMapping("{studentId}/update-result/{subjectId}/subject")
  @PreAuthorize("hasAuthority('C_UPD')")
  public ResponseEntity<ApiMessageDto<SubjectRegistrationDto>> updateStudentResult(@PathVariable int studentId, @PathVariable int subjectId, @RequestParam("studyResultResponse") float result){
    SubjectRegistrationDto subjectRegistrationDto = studentDaoService.updateStudentScore(studentId, subjectId, result);
    ApiMessageDto<SubjectRegistrationDto> response = ApiResponeUtils.results("Cap nhat diem thanh cong", subjectRegistrationDto);
    return ResponseEntity.ok(response);
  }


@GetMapping("/report")
@PreAuthorize("hasAuthority('C_GET')")
public ResponseEntity<ApiMessageDto<StudyReportDto>> getStudentReport() {
  StudyReportDto reportData = studentDaoService.getStudentReport();
  ApiMessageDto<StudyReportDto> response = ApiResponeUtils.results("Bao cao tinh hinh hoc tap", reportData);
  return ResponseEntity.ok(response);
}
}
