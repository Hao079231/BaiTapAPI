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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectRegistration {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  @JsonBackReference
  private Student student;

  @ManyToOne
  @JoinColumn(name = "subject_id")
  @JsonBackReference
  private Subject subject;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateRegister;

  @Enumerated(EnumType.STRING)
  private Status status;

  public enum Status{
    PENDING, ACTIVE
  }
}
