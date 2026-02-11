package andreapascarella.u5d8.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;

    public User(String surname, String name, String email, LocalDate birthDate) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
}
