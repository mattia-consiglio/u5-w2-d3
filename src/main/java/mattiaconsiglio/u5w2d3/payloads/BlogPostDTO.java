package mattiaconsiglio.u5w2d3.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BlogPostDTO(
        @NotBlank(message = "Category is mandatory")
        String category,
        @NotBlank(message = "Title is mandatory")
        String title,
        @NotBlank(message = "Content is mandatory")
        String content,
        @NotNull(message = "Reading time is mandatory")
        int readingTime,
        @NotNull(message = "Author id is mandatory")
        UUID authorId
) {
}
