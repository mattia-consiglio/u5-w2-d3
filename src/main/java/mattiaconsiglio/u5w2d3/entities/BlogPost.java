package mattiaconsiglio.u5w2d3.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "blogposts")
public class BlogPost {
    @Id
    @Setter(value = AccessLevel.NONE)
    @GeneratedValue
    private UUID id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public BlogPost(String category, String title, String content, int readingTime, Author author, String cover) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
        this.cover = cover;
    }
}
