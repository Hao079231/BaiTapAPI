package vn.itz.jpastudying.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.PeriodDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.config.FeignClientConfig;
import vn.itz.jpastudying.model.criteria.PeriodCriteria;

@FeignClient(name = "period-service",  url = "http://localhost:8081/api/period", configuration = FeignClientConfig.class)
public interface PeriodFeignClient {
  @GetMapping("/list")
  ApiMessageDto<ShowPagedResults<PeriodDto>> getPeriodsByCriteria(@SpringQueryMap PeriodCriteria criteria,
      Pageable pageable, @RequestHeader("Authorization") String token);

}
