package mattiaconsiglio.u5w2d3.exceptions;

import java.util.UUID;

public class BlogPostNotFoundException extends RecordNotFoundException {
    public BlogPostNotFoundException(UUID id) {
        super("Blogpost", id);
    }
}
