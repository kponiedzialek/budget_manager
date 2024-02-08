package monday.budget_manager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import monday.budget_manager.validation.annotation.FieldMatch;

@Getter
@Setter
@FieldMatch(first = "password", second = "passwordRepeated", message = "Hasła muszą być takie same.")
public class RegistrationDto {
  @NotNull(message = "Nazwa użytkownika jest wymagana!")
  @Size(min = 2, max = 20, message = "Nazwa użytkownika musi zawierać od 2 do 20 znaków!")
  private String username;

  @NotNull(message = "Hasło użytkownika jest wymagane!")
  @Size(min = 5, message = "Hasło musi składać się z conajmniej 5 znaków!")
  private String password;

  @NotNull(message = "Powtórzone hasło użytkownika jest wymagane!")
  private String passwordRepeated;
}
