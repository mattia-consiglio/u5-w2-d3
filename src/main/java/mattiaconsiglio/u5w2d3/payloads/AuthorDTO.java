package mattiaconsiglio.u5w2d3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 2, message = "Name must be at least 2 characters long")
        String name,
        @NotBlank(message = "Surname is mandatory")
        @Size(min = 2, message = "Surname must be at least 2 characters long")
        String surname,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email")
        String email,
        @NotNull(message = "Birth date is mandatory")
//        @Pattern(regexp = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$", message = "Invalid birth date")
        LocalDate birthDate
) {
}
