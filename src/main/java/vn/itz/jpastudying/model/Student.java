package vn.itz.jpastudying.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.itz.jpastudying.validation.GioiTinh;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String fullname;

  private Date birthday;

  @Column(nullable = false)
  private String password;

  @GioiTinh
  @Column(nullable = false)
  private String gender;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<SubjectRegistration> registrations = new ArrayList<>();

}
