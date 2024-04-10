package mattiaconsiglio.u5w2d3.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AuthorPayload {
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
}
