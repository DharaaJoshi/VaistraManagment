package Vaistra.Managment.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotNull(message = "Username should not be null!")
    private String username;

    @NotNull(message = "Password should not be null!")
    private String password;
}
