package vn.itz.jpastudying.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Students;
import vn.itz.jpastudying.model.User;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
  Optional<Students> findByUser(User user);
}
