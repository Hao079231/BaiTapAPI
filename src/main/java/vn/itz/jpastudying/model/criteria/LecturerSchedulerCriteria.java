package vn.itz.jpastudying.model.criteria;

import lombok.Data;

@Data
public class LecturerSchedulerCriteria {
  private Long id;
  private Long lecturerId;
  private Integer courseId;
  private Integer sortById;
}
