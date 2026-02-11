package andreapascarella.u5d8.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class NewUserPayload {

    private String name;
    private String surname;
    private String email;
    private String avatar;
    private LocalDate birthDate;
}