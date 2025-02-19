package vn.itz.jpastudying.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.LecturerRegisterTeachingDto;
import vn.itz.jpastudying.form.lecturer.LecturerRegisterTeachingForm;

@FeignClient(name = "lecturer-scheduler-service",  url = "http://localhost:8081/api/lecturer-scheduler")
public interface LecturerSchedulerFeignClient {

  @PostMapping("/create")
  ApiMessageDto<LecturerRegisterTeachingDto> registerTeaching(@RequestBody LecturerRegisterTeachingForm request);
}
