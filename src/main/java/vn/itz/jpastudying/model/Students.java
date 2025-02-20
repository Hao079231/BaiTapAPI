package vn.itz.jpastudying.model;

import javax.persistence.*;
import java.util.Date;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class Students extends Auditable{
  @Id
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_students_user"))
  private User user;

  @Column(nullable = false, unique = true)
  private String mssv;

  private Date birthday;
}
