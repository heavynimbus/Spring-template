package heavynimbus.backend.controller.doc;

import heavynimbus.backend.dto.login.LoginRequest;
import heavynimbus.backend.dto.login.LoginResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "Authentication", description = "How to be authenticated")
public interface LoginControllerDocumentation {
   LoginResponse authenticate(
      @RequestBody(
              required = true,
              description = "Login request containing username & password",
              content =
                  @Content(
                      schema = @Schema(implementation = LoginRequest.class),
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      examples =
                          @ExampleObject(
                              summary = "login request example",
                              description = "With valid credentials",
                              value = """
                                      {
                                        "username": "HeavyNimbus",
                                        "password": "Maille_Sikret_Pazouaurd"
                                      }
                                      """)))
          LoginRequest loginRequest);
}
