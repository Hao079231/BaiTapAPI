package vn.itz.jpastudying.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Students;
import vn.itz.jpastudying.model.User;
import vn.itz.jpastudying.projections.StudentInfoProjection;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
  Optional<Students> findByUser(User user);

  @Query("SELECT u.id AS id, u.username AS username, u.fullname AS fullname, s.mssv AS mssv, s.birthday AS birthday " +
      "FROM Students s JOIN s.user u")
  List<StudentInfoProjection> findAllStudentInfo();
}
