package vn.itz.jpastudying.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lecturer {
  @Id
  private Long id;

  @Column(nullable = false)
  private String career;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_lecturer_user"))
  private User user;
}
