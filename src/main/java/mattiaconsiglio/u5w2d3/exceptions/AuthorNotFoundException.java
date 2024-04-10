package mattiaconsiglio.u5w2d3.exceptions;

import java.util.UUID;

public class AuthorNotFoundException extends RecordNotFoundException {
    public AuthorNotFoundException(UUID id) {
        super("Author", id);
    }
}
