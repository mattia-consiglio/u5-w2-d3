package mattiaconsiglio.u5w2d3.controller;

import mattiaconsiglio.u5w2d3.entities.BlogPost;
import mattiaconsiglio.u5w2d3.services.BlogPostPayload;
import mattiaconsiglio.u5w2d3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;


    @GetMapping
    public List<BlogPost> getBlogPosts() {
        return this.blogPostService.getBlogPosts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost addBlogPost(@RequestBody BlogPostPayload blogPost) {
        return this.blogPostService.addBlogPost(blogPost);
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPost(@PathVariable("id") UUID id) {
        return this.blogPostService.getBlogPost(id);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable("id") UUID id, @RequestBody BlogPostPayload blogPost) {
        return this.blogPostService.updateBlogPost(id, blogPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable("id") UUID id) {
        this.blogPostService.deleteBlogPost(id);
    }

}
