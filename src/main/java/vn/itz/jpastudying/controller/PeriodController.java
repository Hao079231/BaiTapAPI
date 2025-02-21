package vn.itz.jpastudying.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.PeriodDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.feign.PeriodFeignClient;
import vn.itz.jpastudying.form.period.PeriodCreateForm;
import vn.itz.jpastudying.form.period.PeriodUpdateForm;
import vn.itz.jpastudying.model.criteria.PeriodCriteria;

@RestController
@RequestMapping("/period")
public class PeriodController {

  @Autowired
  private PeriodFeignClient periodFeignClient;

  // Ham lay danh sach Period criteria
  @GetMapping("/list")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<PeriodDto>>> getPeriods(
      PeriodCriteria periodCriteria,
      Pageable pageable, @RequestHeader("Authorization") String token){
    ApiMessageDto<ShowPagedResults<PeriodDto>> response = periodFeignClient.getPeriodsByCriteria(periodCriteria, pageable, token);
    return ResponseEntity.ok(response);
  }

  // Ham lay thong tin Period bang id
  @GetMapping("/get/{id}")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<PeriodDto>> getPeriodById(@PathVariable Long id, @RequestHeader("Authorization") String token){
    ApiMessageDto<PeriodDto> response = periodFeignClient.getPeriodById(id, token);
    return ResponseEntity.ok(response);
  }


  // Ham tao Period moi
  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<PeriodDto>> createPeriod(
      @Valid @RequestBody PeriodCreateForm request, @RequestHeader("Authorization") String token){
    ApiMessageDto<PeriodDto> response = periodFeignClient.createPeriod(request, token);
    return ResponseEntity.ok(response);
  }

  // Ham cap nhat thong tin Period
  @PutMapping("/update")
  @PreAuthorize("hasAuthority('C_UPD')")
  public ResponseEntity<ApiMessageDto<PeriodDto>> updatePeriod(
      @Valid @RequestBody PeriodUpdateForm request, @RequestHeader("Authorization") String token){
    ApiMessageDto<PeriodDto> response = periodFeignClient.updatePeriod(request, token);
    return ResponseEntity.ok(response);
  }

  // Ham xoa thong tin Period
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAuthority('C_DEL')")
  public ResponseEntity<ApiMessageDto<String>> deletePeriod(
      @PathVariable Long id, @RequestHeader("Authorization") String token){
    ApiMessageDto<String> response = periodFeignClient.deletePeriod(id, token);
    return ResponseEntity.ok(response);
  }
}
