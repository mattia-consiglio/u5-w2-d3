package mattiaconsiglio.u5w2d3.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import mattiaconsiglio.u5w2d3.entities.Author;
import mattiaconsiglio.u5w2d3.entities.BlogPost;
import mattiaconsiglio.u5w2d3.exceptions.BlogPostNotFoundException;
import mattiaconsiglio.u5w2d3.payloads.BlogPostDTO;
import mattiaconsiglio.u5w2d3.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository br;
    @Autowired
    private AuthorService as;

    @Autowired
    private Cloudinary cloudinary;

    public BlogPost addBlogPost(BlogPostDTO blogPost) {
        Author author = as.getAuthor(blogPost.authorId());

        return br.save(new BlogPost(blogPost.category(), blogPost.title(), blogPost.content(), blogPost.readingTime(), author, "https://picsum.photos/200/300"))
                ;
    }

    public BlogPost getBlogPost(UUID id) {
        return br.findById(id).orElseThrow(() -> new BlogPostNotFoundException(id));
    }

    public Page<BlogPost> getBlogPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title"));
        return br.findAll(pageable);
    }


    public BlogPost updateBlogPost(UUID id, BlogPostDTO blogPost) {
        BlogPost b = this.getBlogPost(id);
        b.setTitle(blogPost.title());
        b.setContent(blogPost.content());
        b.setCategory(blogPost.category());
        b.setReadingTime(blogPost.readingTime());
        return br.save(b);
    }

    public void deleteBlogPost(UUID id) {
        this.getBlogPost(id);
        br.deleteById(id);
    }


    public BlogPost updateBlogPostCover(UUID id, MultipartFile file) throws IOException {
        BlogPost b = this.getBlogPost(id);
        file.getContentType();
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        b.setCover(url);
        return br.save(b);
    }
}
