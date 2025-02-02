package vn.itz.jpastudying.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StudentSubject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  private Student student;
  @ManyToOne
  private Subject subject;

  public StudentSubject() {
  }

  public StudentSubject(Student student, Subject subject) {
    this.student = student;
    this.subject = subject;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }
}
