package vn.itz.jpastudying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
