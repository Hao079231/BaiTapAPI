package vn.itz.jpastudying.feign;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.PeriodDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.config.FeignClientConfig;
import vn.itz.jpastudying.form.period.PeriodCreateForm;
import vn.itz.jpastudying.form.period.PeriodUpdateForm;
import vn.itz.jpastudying.model.criteria.PeriodCriteria;

@FeignClient(name = "period-service",  url = "http://localhost:8081/api/period", configuration = FeignClientConfig.class)
public interface PeriodFeignClient {
  @GetMapping("/list")
  ApiMessageDto<ShowPagedResults<PeriodDto>> getPeriodsByCriteria(@SpringQueryMap PeriodCriteria criteria,
      Pageable pageable, @RequestHeader("Authorization") String token);

  @PostMapping("/create")
  ApiMessageDto<PeriodDto> createPeriod(@RequestBody PeriodCreateForm request, @RequestHeader("Authorization") String token);

  @GetMapping("/get/{id}")
  ApiMessageDto<PeriodDto> getPeriodById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);

  @PutMapping("/update")
  ApiMessageDto<PeriodDto> updatePeriod(@RequestBody PeriodUpdateForm request, @RequestHeader("Authorization") String token);

  @DeleteMapping("/delete/{id}")
  ApiMessageDto<String> deletePeriod(@PathVariable("id") Long id,  @RequestHeader("Authorization") String token);
}
