package vn.itz.jpastudying.model;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin extends Auditable{
  @Id
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_admin_user"))
  private User user;

  private Integer level;

  private boolean superAdmin;
}
