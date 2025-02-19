package vn.itz.jpastudying.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.itz.jpastudying.model.Admin;
import vn.itz.jpastudying.model.User;
import vn.itz.jpastudying.projections.AdminInfoProjection;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
  @Query("SELECT us.id AS id, us.username AS username, us.fullname AS fullname, ad.level AS level " +
      "FROM Admin ad JOIN ad.user us")
  List<AdminInfoProjection> findAllAdminInfo();

  Optional<Admin> findByUser(User user);
}
