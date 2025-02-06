package vn.itz.jpastudying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.itz.jpastudying.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>,
    JpaSpecificationExecutor<Subject> {
  boolean existsByName(String name);
  boolean existsByCode(String code);
}
