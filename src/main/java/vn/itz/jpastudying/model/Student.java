package vn.itz.jpastudying.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int student_id;

  @NotNull(message = "Username khong the null")
  @Size(min = 2, message = "Username phai it nhat co 2 ky tu")
  @Column(nullable = false, unique = true)
  private String username;

  @NotNull(message = "Fullname khong the null")
  @Size(min = 2, message = "Fullname phai it nhat co 2 ky tu")
  @Column(nullable = false)
  private String fullname;

  @NotNull(message = "Birthday khong the null")
  @Past(message = "Ngay sinh phai la trong qua khu")
  private Date birthday;

  @NotNull(message = "Password khong the null")
  @Size(min = 5, message = "Password phai it nhat co 5 ky tu")
  @Column(nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "student_subject",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
  private List<Subject> subjects = new ArrayList<>();

  public Student() {
  }

  public Student(String username, String fullname, Date birthday, String password) {
    this.username = username;
    this.fullname = fullname;
    this.birthday = birthday;
    this.password = password;
  }

  public int getStudent_id() {
    return student_id;
  }

  public void setId(int student_id) {
    this.student_id = student_id;
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

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }
}
