package vn.itz.jpastudying.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.Dto.StudyReportDto;
import vn.itz.jpastudying.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,
    JpaSpecificationExecutor<Student> {

  boolean existsByUsername(String username);

  Optional<Student> findByUsername(String username);

  @Query("SELECT new vn.itz.jpastudying.Dto.StudyReportDto(" +
      "COUNT(DISTINCT sub.id), COUNT(DISTINCT s.id), COALESCE(AVG(sr.studyResult), 0), " +
      "COUNT(DISTINCT CASE WHEN s.gender = '1' THEN s.id END), " +
      "COUNT(DISTINCT CASE WHEN s.gender = '2' THEN s.id END)) " +
      "FROM Student s " +
      "LEFT JOIN SubjectRegistration sr ON s.id = sr.student.id " +
      "LEFT JOIN Subject sub ON sr.subject.id = sub.id")
  StudyReportDto getReportData();

}
