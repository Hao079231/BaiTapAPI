package vn.itz.jpastudying.model;

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
  private int id;
  private String name;
  private String code;

  @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
  private List<StudentSubject> studentSubjects;

  public Subject() {
  }

  public Subject(int id, String name, String code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
