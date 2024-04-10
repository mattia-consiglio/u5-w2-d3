package mattiaconsiglio.u5w2d3.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BlogPostPayload {
    private String category;
    private String title;
    private String content;
    private int readingTime;
    private UUID authorId;
}
