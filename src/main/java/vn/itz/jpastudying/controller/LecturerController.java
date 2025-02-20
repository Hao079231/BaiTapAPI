package vn.itz.jpastudying.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.LecturerDto;
import vn.itz.jpastudying.Dto.LecturerRegisterTeachingDto;
import vn.itz.jpastudying.Dto.PeriodDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.feign.LecturerSchedulerFeignClient;
import vn.itz.jpastudying.feign.PeriodFeignClient;
import vn.itz.jpastudying.form.lecturer.LecturerCreateForm;
import vn.itz.jpastudying.form.lecturer.LecturerRegisterTeachingForm;
import vn.itz.jpastudying.model.criteria.PeriodCriteria;
import vn.itz.jpastudying.service.LecturerDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {
  @Autowired
  private LecturerDaoService lecturerDaoService;

  @Autowired
  private PeriodFeignClient periodFeignClient;

  @Autowired
  private LecturerSchedulerFeignClient lecturerSchedulerFeignClient;

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<LecturerDto>> registerLecturer(@RequestBody
      LecturerCreateForm request){
    ApiMessageDto<LecturerDto> response = ApiResponeUtils.results("Dang ky giang vien thanh cong",
        lecturerDaoService.registerLecturer(request));
    return ResponseEntity.ok(response);
  }

  // Ham lay danh sach Period criteria
  @GetMapping("/period/list")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<PeriodDto>>> getPeriods(PeriodCriteria periodCriteria,
      Pageable pageable, @RequestHeader("Authorization") String token){
    ApiMessageDto<ShowPagedResults<PeriodDto>> response = periodFeignClient.getPeriodsByCriteria(periodCriteria, pageable, token);
    return ResponseEntity.ok(response);
  }

  // Ham dang ky lich day cho giao vien
  @PostMapping("/register")
  public ResponseEntity<ApiMessageDto<LecturerRegisterTeachingDto>> registerTeachinh(@Valid @RequestBody
      LecturerRegisterTeachingForm request){
    ApiMessageDto<LecturerRegisterTeachingDto> response = lecturerSchedulerFeignClient.registerTeaching(request);
    return ResponseEntity.ok(response);
  }
}
