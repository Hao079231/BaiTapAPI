package vn.itz.jpastudying.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,
    JpaSpecificationExecutor<Student> {
  boolean existsByUsername(String username);
  Optional<Student> findByUsername(String username);
  @Query(value = " SELECT "
      + "(SELECT COUNT(*) FROM subject) AS tongKhoaHoc,"
      + "(SELECT COUNT(*) FROM student) AS tongSinhVien,"
      + "(SELECT AVG(sr.result) FROM subject_registration sr) AS diemTrungBinh,"
      + "(SELECT COUNT(*) FROM student WHERE gender = '1') AS sinhVienNam,"
      + "(SELECT COUNT(*) FROM student WHERE gender = '2') AS sinhVienNu", nativeQuery = true)
  List<Object[]> getReportData();

}
