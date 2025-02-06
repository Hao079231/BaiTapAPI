package vn.itz.jpastudying.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//  @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(
//      name = "student_subject",
//      joinColumns = @JoinColumn(name = "student_id"),
//      inverseJoinColumns = @JoinColumn(name = "subject_id")
//  )
//  private List<Subject> subjects = new ArrayList<>();

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<SubjectRegistration> registrations = new ArrayList<>();

}
