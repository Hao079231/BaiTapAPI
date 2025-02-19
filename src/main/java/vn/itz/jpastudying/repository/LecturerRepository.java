package vn.itz.jpastudying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}
