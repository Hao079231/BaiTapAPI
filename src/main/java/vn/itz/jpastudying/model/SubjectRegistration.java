package vn.itz.jpastudying.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SubjectRegistration {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int registration_id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  @JsonBackReference
  private Student student;

  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Subject subject;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateRegister;

  @Enumerated(EnumType.STRING)
  private Status status;

  public enum Status{
    PENDING, ACTIVE
  }

  public int getRegistration_id() {
    return registration_id;
  }

  public void setRegistration_id(int registration_id) {
    this.registration_id = registration_id;
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

  public Date getDateRegister() {
    return dateRegister;
  }

  public void setDateRegister(Date dateRegister) {
    this.dateRegister = dateRegister;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
