package mattiaconsiglio.u5w2d3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Setter(value = AccessLevel.NONE)
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String avatar;

    public Author(String name, String surname, String email, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public void generateAvatar() {
        this.avatar = "https://ui-avatars.com/api/?name=" + this.name.charAt(0) + "+" + this.surname.charAt(0);
    }
}
