package vn.itz.jpastudying.model;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends Auditable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  private String avatar;

  @Column(nullable = false)
  private String fullname;

  @Column(nullable = false)
  private String gender;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;
}
