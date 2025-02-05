package vn.itz.jpastudying.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int subject_id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false, unique = true)
  private String code;

//  @ManyToMany(mappedBy = "subjects")
//  private List<Student> students = new ArrayList<>();

  @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<SubjectRegistration> registrations = new ArrayList<>();
}
