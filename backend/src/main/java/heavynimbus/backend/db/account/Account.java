package heavynimbus.backend.db.account;

import heavynimbus.backend.db.accountRole.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class Account {

  @Id
  @Column(name = "id")
  private UUID id;

  @NotNull
  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "account_roles",
      joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<AccountRole> roles;
}
