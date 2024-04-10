package mattiaconsiglio.u5w2d3.services;

import mattiaconsiglio.u5w2d3.entities.Author;
import mattiaconsiglio.u5w2d3.exceptions.AuthorNotFoundException;
import mattiaconsiglio.u5w2d3.exceptions.BadRequestException;
import mattiaconsiglio.u5w2d3.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository ar;


    public List<Author> getAuthors() {
        return ar.findAll();
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
