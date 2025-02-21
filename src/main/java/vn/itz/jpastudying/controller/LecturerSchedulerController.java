package vn.itz.jpastudying.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.LecturerRegisterTeachingDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.feign.LecturerSchedulerFeignClient;
import vn.itz.jpastudying.form.lecturerscheduler.LecturerSchedulerCreateForm;
import vn.itz.jpastudying.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import vn.itz.jpastudying.model.Lecturer;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.model.criteria.LecturerSchedulerCriteria;
import vn.itz.jpastudying.repository.LecturerRepository;
import vn.itz.jpastudying.repository.SubjectRepository;

@RestController
@RequestMapping("/lecturer-scheduler")
public class LecturerSchedulerController {
  @Autowired
  private LecturerSchedulerFeignClient lecturerSchedulerFeignClient;

  @Autowired
  private LecturerRepository lecturerRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  // Ham lay danh sach Lecturer Scheduler criteria
  @GetMapping("/list")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<LecturerRegisterTeachingDto>>> getListLecturerSchedulerCriteria(
    LecturerSchedulerCriteria request, Pageable pageable, @RequestHeader("Authorization") String token
  ){
      ApiMessageDto<ShowPagedResults<LecturerRegisterTeachingDto>> response = lecturerSchedulerFeignClient.getListLecturerSchedulerCriteria(request, pageable, token);
      return ResponseEntity.ok(response);
  }
  //Ham lay thong tin Lecturer Scheduler
  @GetMapping("/get/{id}")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<LecturerRegisterTeachingDto>> getLecturerSchedulerById(@PathVariable Long id,
      @RequestHeader("Authorization") String token){
    ApiMessageDto<LecturerRegisterTeachingDto> response = lecturerSchedulerFeignClient.getLecturerSchedulerById(id, token);
    return ResponseEntity.ok(response);
  }

  // Ham dang ky lich day cho giao vien
  @PostMapping("/register")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<LecturerRegisterTeachingDto>> registerTeaching(@Valid @RequestBody
  LecturerSchedulerCreateForm request, @RequestHeader("Authorization") String token){
    Lecturer lecturer = lecturerRepository.findById(request.getLecturerIdValue()).orElseThrow(()
    -> new ResourceNotFound("Giao vien khong ton tai", HttpStatus.NOT_FOUND));

    Subject subject = subjectRepository.findById(request.getCourseIdValue()).orElseThrow(()
    -> new ResourceNotFound("Khoa hoc khong ton tai", HttpStatus.NOT_FOUND));

    ApiMessageDto<LecturerRegisterTeachingDto> response = lecturerSchedulerFeignClient.registerTeaching(request, token);
    return ResponseEntity.ok(response);
  }

  // Ham cap nhat lich day cho giang vien
  @PutMapping("/update")
  @PreAuthorize("hasAuthority('C_UPD')")
  public ResponseEntity<ApiMessageDto<LecturerRegisterTeachingDto>> updateLecturerScheduler(@Valid @RequestBody
  LecturerSchedulerUpdateForm request, @RequestHeader("Authorization") String token){
    Lecturer lecturer = lecturerRepository.findById(request.getLecturerIdValue()).orElseThrow(()
        -> new ResourceNotFound("Giao vien khong ton tai", HttpStatus.NOT_FOUND));

    Subject subject = subjectRepository.findById(request.getCourseIdValue()).orElseThrow(()
        -> new ResourceNotFound("Khoa hoc khong ton tai", HttpStatus.NOT_FOUND));

    ApiMessageDto<LecturerRegisterTeachingDto> response = lecturerSchedulerFeignClient.updateLecturerScheduler(request, token);
    return ResponseEntity.ok(response);
  }

  // Ham xoa lich day cua giang vien
  @DeleteMapping("delete/{id}")
  @PreAuthorize("hasAuthority('C_DEL')")
  public ResponseEntity<ApiMessageDto<String>> deleteLecturerScheduler(@PathVariable Long id,
      @RequestHeader("Authorization") String token){
    ApiMessageDto<String> response = lecturerSchedulerFeignClient.deleteLecturerScheduler(id, token);
    return ResponseEntity.ok(response);
  }
}
