package heavynimbus.backend.dto.login;

import lombok.Data;

@Data
public class LoginRequest {
  private String username;
  private String password;
}
