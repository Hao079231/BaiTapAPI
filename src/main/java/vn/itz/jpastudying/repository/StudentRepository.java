package vn.itz.jpastudying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,
    JpaSpecificationExecutor<Student> {
  boolean existsByUsername(String username);
}
