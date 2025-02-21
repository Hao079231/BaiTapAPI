package vn.itz.jpastudying.form.period;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodCreateForm {
  @ApiModelProperty(value = "Ten hoc ki", example = "Hoc ki I", required = true)
  @NotEmpty(message = "Ten ki khong the trong")
  private String periodName;

  @ApiModelProperty(value = "Mo ta hoc ki", example = "Hoc ki nay rat quan trong", required = true)
  private String periodDescription;

  @ApiModelProperty(value = "Ngay bat dau hoc ki", example = "20-01-2025", required = true)
  @Future(message = "Ngay bat dau ki hoc phai la ngay o tuong lai")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate periodStartDate;

  @ApiModelProperty(value = "Ngay ket thuc hoc ki", example = "20-01-2025", required = true)
  @Future(message = "Ngay ket thuc ki hoc phai la ngay o tuong lai")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate periodDueDate;

  @ApiModelProperty(value = "Trang thai hoc ki: 0 - Init, 1 - Recruit, 2 - Done", example = "0", required = true)
  @NotNull(message = "Trang thai cua ki khong duoc de trong")
  private Integer periodState;
}
