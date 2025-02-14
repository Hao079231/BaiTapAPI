package vn.itz.jpastudying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
