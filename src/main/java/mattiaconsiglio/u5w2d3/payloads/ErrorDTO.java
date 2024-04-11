package mattiaconsiglio.u5w2d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private LocalDateTime timestamp;
}
