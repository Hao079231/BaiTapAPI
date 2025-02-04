package vn.itz.jpastudying.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
  boolean existsByUsername(String username);
}
