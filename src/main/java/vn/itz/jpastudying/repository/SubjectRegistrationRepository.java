package vn.itz.jpastudying.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.model.SubjectRegistration;

public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Integer>, JpaSpecificationExecutor<SubjectRegistration> {
  @Transactional
  @Modifying
  @Query("DELETE FROM SubjectRegistration sr WHERE sr.student.id = :studentId AND sr.subject.id = :subjectId")
  void deleteByStudentIdAndSubjectId(@Param("studentId") int studentId, @Param("subjectId") int subjectId);
  boolean existsByStudentIdAndSubjectId(int studentId, int subjectId);

  Optional<Object> findByStudentIdAndSubjectId(int studentId, int subjectId);

  List<SubjectRegistration> findBySubject(Subject subject);
}
