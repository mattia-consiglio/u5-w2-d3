package mattiaconsiglio.u5w2d3.controller;

import mattiaconsiglio.u5w2d3.entities.Author;
import mattiaconsiglio.u5w2d3.services.AuthorPayload;
import mattiaconsiglio.u5w2d3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService as;

    @GetMapping
    public List<Author> getAuthors() {
        return as.getAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") UUID id) {
        return as.getAuthor(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        return as.addAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable("id") UUID id, @RequestBody AuthorPayload author) {
        return as.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("id") UUID id) {
        as.deleteAuthor(id);
    }
}
