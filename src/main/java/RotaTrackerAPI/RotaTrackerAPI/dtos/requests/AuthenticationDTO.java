package RotaTrackerAPI.RotaTrackerAPI.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "O login nao pode ser vazio")
        String login,
        @NotBlank(message = "A senha nao pode ser vazia")
        String password
) {
}
