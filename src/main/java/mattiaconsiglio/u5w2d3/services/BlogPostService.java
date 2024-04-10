package mattiaconsiglio.u5w2d3.services;

import mattiaconsiglio.u5w2d3.entities.Author;
import mattiaconsiglio.u5w2d3.entities.BlogPost;
import mattiaconsiglio.u5w2d3.exceptions.BlogPostNotFoundException;
import mattiaconsiglio.u5w2d3.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository br;
    @Autowired
    private AuthorService as;

    public BlogPost addBlogPost(BlogPostPayload blogPost) {
        Author author = as.getAuthor(blogPost.getAuthorId());
        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setTitle(blogPost.getTitle());
        newBlogPost.setContent(blogPost.getContent());
        newBlogPost.setAuthor(author);
        newBlogPost.setCategory(blogPost.getCategory());
        newBlogPost.setReadingTime(blogPost.getReadingTime());
        newBlogPost.setCover("https://picsum.photos/200/300");
        return br.save(newBlogPost);
    }

    public BlogPost getBlogPost(UUID id) {
        return br.findById(id).orElseThrow(() -> new BlogPostNotFoundException(id));
    }

    public List<BlogPost> getBlogPosts() {
        return br.findAll();
    }


    public BlogPost updateBlogPost(UUID id, BlogPostPayload blogPost) {
        BlogPost b = this.getBlogPost(id);
        b.setTitle(blogPost.getTitle());
        b.setContent(blogPost.getContent());
        b.setCategory(blogPost.getCategory());
        b.setReadingTime(blogPost.getReadingTime());
        return br.save(b);
    }

    public void deleteBlogPost(UUID id) {
        this.getBlogPost(id);
        br.deleteById(id);
    }
}
