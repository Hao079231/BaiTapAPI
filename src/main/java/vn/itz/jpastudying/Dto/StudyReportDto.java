package vn.itz.jpastudying.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyReportDto {
  private long tongKhoaHoc;
  private long tongSinhVien;
  private double diemTrungBinh;
  private long sinhVienNam;
  private long sinhVienNu;
}
