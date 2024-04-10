package mattiaconsiglio.u5w2d3.services;

import mattiaconsiglio.u5w2d3.entities.Author;
import mattiaconsiglio.u5w2d3.exceptions.AuthorNotFoundException;
import mattiaconsiglio.u5w2d3.exceptions.BadRequestException;
import mattiaconsiglio.u5w2d3.payloads.AuthorPayload;
import mattiaconsiglio.u5w2d3.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository ar;


    public Page<Author> getAuthors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return ar.findAll(pageable);
    }

    public Author addAuthor(Author author) {
        if (ar.existsByEmail(author.getEmail())) {
            throw new BadRequestException("Email already in use");
        }
        author.generateAvatar();
        return ar.save(author);
    }

    public Author getAuthor(UUID id) {
        return ar.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public Author updateAuthor(UUID id, AuthorPayload author) {
        Author a = this.getAuthor(id);
        a.setName(author.getName());
        a.setSurname(author.getSurname());
        a.setEmail(author.getEmail());
        a.setBirthDate(author.getBirthDate());
        a.generateAvatar();
        return ar.save(a);
    }

    public void deleteAuthor(UUID id) {
        this.getAuthor(id);
        ar.deleteById(id);
    }


}
