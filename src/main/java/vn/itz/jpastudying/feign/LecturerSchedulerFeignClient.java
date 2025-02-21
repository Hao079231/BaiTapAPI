package vn.itz.jpastudying.feign;

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
import vn.itz.jpastudying.Dto.LecturerRegisterTeachingDto;
import vn.itz.jpastudying.Dto.ShowPagedResults;
import vn.itz.jpastudying.config.FeignClientConfig;
import vn.itz.jpastudying.form.lecturerscheduler.LecturerSchedulerCreateForm;
import vn.itz.jpastudying.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import vn.itz.jpastudying.model.criteria.LecturerSchedulerCriteria;

@FeignClient(name = "lecturer-scheduler-service",  url = "http://localhost:8081/api/lecturer-scheduler", configuration = FeignClientConfig.class)
public interface LecturerSchedulerFeignClient {

  @GetMapping("/list")
  ApiMessageDto<ShowPagedResults<LecturerRegisterTeachingDto>> getListLecturerSchedulerCriteria(
      @SpringQueryMap LecturerSchedulerCriteria request, Pageable pageable, @RequestHeader("Authorization") String token);

  @GetMapping("get/{id}")
  ApiMessageDto<LecturerRegisterTeachingDto> getLecturerSchedulerById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);

  @PostMapping("/create")
  ApiMessageDto<LecturerRegisterTeachingDto> registerTeaching(@RequestBody LecturerSchedulerCreateForm request,
      @RequestHeader("Authorization") String token);

  @PutMapping("/update")
  ApiMessageDto<LecturerRegisterTeachingDto> updateLecturerScheduler(@RequestBody
      LecturerSchedulerUpdateForm request, @RequestHeader("Authorization") String token);

  @DeleteMapping("/delete/{id}")
  ApiMessageDto<String> deleteLecturerScheduler(@PathVariable("id") Long id,
      @RequestHeader("Authorization") String token);
}
