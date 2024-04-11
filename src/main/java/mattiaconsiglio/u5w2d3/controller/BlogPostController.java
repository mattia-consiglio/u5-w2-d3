package mattiaconsiglio.u5w2d3.controller;

import mattiaconsiglio.u5w2d3.entities.BlogPost;
import mattiaconsiglio.u5w2d3.exceptions.BadRequestException;
import mattiaconsiglio.u5w2d3.payloads.BlogPostDTO;
import mattiaconsiglio.u5w2d3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;


    @GetMapping
    public Page<BlogPost> getBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return this.blogPostService.getBlogPosts(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost addBlogPost(@RequestBody @Validated BlogPostDTO blogPost, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.blogPostService.addBlogPost(blogPost);
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPost(@PathVariable("id") UUID id) {
        return this.blogPostService.getBlogPost(id);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable("id") UUID id, @RequestBody @Validated BlogPostDTO blogPost, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.blogPostService.updateBlogPost(id, blogPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable("id") UUID id) {
        this.blogPostService.deleteBlogPost(id);
    }

}
