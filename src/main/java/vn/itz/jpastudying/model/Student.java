package vn.itz.jpastudying.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull(message = "Username cannot be null")
  private String username;

  @NotNull(message = "Fullname cannot be null")
  private String fullname;

  @NotNull(message = "Birthday cannot be null")
  private Date birthday;

  @NotNull(message = "Password cannot be null")
  private String password;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  private List<StudentSubject> studentSubjects;

  public Student() {
  }

  public Student(String username, String fullname, Date birthday, String password) {
    this.username = username;
    this.fullname = fullname;
    this.birthday = birthday;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<StudentSubject> getStudentSubjects() {
    return studentSubjects;
  }

  public void setStudentSubjects(List<StudentSubject> studentSubjects) {
    this.studentSubjects = studentSubjects;
  }
}
