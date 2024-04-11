package mattiaconsiglio.u5w2d3.controller;

import mattiaconsiglio.u5w2d3.entities.Author;
import mattiaconsiglio.u5w2d3.exceptions.BadRequestException;
import mattiaconsiglio.u5w2d3.payloads.AuthorDTO;
import mattiaconsiglio.u5w2d3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService as;

    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return as.getAuthors(page, size);
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") UUID id) {
        return as.getAuthor(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody @Validated AuthorDTO author, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return as.addAuthor(new Author(author.name(), author.surname(), author.email(), author.birthDate()));
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable("id") UUID id, @RequestBody @Validated AuthorDTO author, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return as.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("id") UUID id) {
        as.deleteAuthor(id);
    }

    @PostMapping("/{id}/avatar")
    public Author uploadImage(@PathVariable("id") UUID id, @RequestParam("avatar") MultipartFile file) throws IOException {
        return as.uploadImage(id, file);
    }
}
