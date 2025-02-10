package vn.itz.jpastudying.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.itz.jpastudying.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>,
    JpaSpecificationExecutor<Subject> {
  boolean existsByName(String name);
  boolean existsByCode(String code);

  // Tu dong chuyen doi trang thai cua mot khoa hoc thanh true khi tat ca sinh vien da hoan thanh
  @Modifying
  @Query("UPDATE Subject s SET s.statusSubject = true WHERE s.id IN (" +
      "SELECT sr.subject.id FROM SubjectRegistration sr " +
      "GROUP BY sr.subject.id HAVING COUNT(sr.id) > 0 " +
      "AND SUM(CASE WHEN sr.status <> 'COMPLETE' THEN 1 ELSE 0 END) = 0)")
  @Transactional
  int updateCompletedSubjects();

  // Liet ke ten cac khoa hoc da DONE
  @Query("SELECT s.name FROM Subject s WHERE s.statusSubject = true")
  List<String> findCompletedSubjects();
}
