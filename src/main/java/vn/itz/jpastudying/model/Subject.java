package vn.itz.jpastudying.model;

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

@Entity
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
  private List<SubjectRegistration> registrations = new ArrayList<>();

  public Subject() {
  }

  public Subject(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public int getSubject_id() {
    return subject_id;
  }

  public void setSubject_id(int subject_id) {
    this.subject_id = subject_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
