package vn.itz.jpastudying.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StudentSubject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int register_id;
  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;
  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Subject subject;

  public StudentSubject() {
  }

  public StudentSubject(Student student, Subject subject) {
    this.student = student;
    this.subject = subject;
  }

  public int getRegister_id() {
    return register_id;
  }

  public void setId(int register_id) {
    this.register_id = register_id;
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
