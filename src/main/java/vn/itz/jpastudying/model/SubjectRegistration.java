package vn.itz.jpastudying.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
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
  @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subject_registration_student"))
  @JsonBackReference
  private Student student;

  @ManyToOne
  @JoinColumn(name = "subject_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subject_registration_subject"))
  @JsonBackReference
  private Subject subject;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateRegister;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "result")
  private float studyResult;

  public enum Status{
    PENDING, ACTIVE, COMPLETE
  }
}
