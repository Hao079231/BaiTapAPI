package vn.itz.jpastudying.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int subject_id;
  private String name;
  private String code;

  @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<StudentSubject> studentSubjects;

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

  public List<StudentSubject> getStudentSubjects() {
    return studentSubjects;
  }

  public void setStudentSubjects(List<StudentSubject> studentSubjects) {
    this.studentSubjects = studentSubjects;
  }
}
