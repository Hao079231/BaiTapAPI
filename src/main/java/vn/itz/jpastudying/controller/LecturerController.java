package vn.itz.jpastudying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.LecturerDto;
import vn.itz.jpastudying.form.lecturer.LecturerCreateForm;
import vn.itz.jpastudying.service.LecturerDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {
  @Autowired
  private LecturerDaoService lecturerDaoService;

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<LecturerDto>> registerLecturer(@RequestBody
      LecturerCreateForm request){
    ApiMessageDto<LecturerDto> response = ApiResponeUtils.results("Dang ky giang vien thanh cong",
        lecturerDaoService.registerLecturer(request));
    return ResponseEntity.ok(response);
  }
}
