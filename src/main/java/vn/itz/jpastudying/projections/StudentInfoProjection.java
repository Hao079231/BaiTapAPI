package vn.itz.jpastudying.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public interface StudentInfoProjection {
  Long getId();
  String getUsername();
  String getFullname();
  String getMssv();
  @JsonFormat(pattern = "dd-MM-yyyy")
  Date getBirthday();
}
