package andreapascarella.u5d8.controllers;

import andreapascarella.u5d8.entities.Blog;
import andreapascarella.u5d8.payloads.NewBlogPayload;
import andreapascarella.u5d8.services.BlogsService;
import andreapascarella.u5d8.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    private final BlogsService blogsService;
    private final UsersService usersService;

    @Autowired
    public BlogsController(BlogsService blogsService, UsersService usersService) {
        this.blogsService = blogsService;
        this.usersService = usersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody NewBlogPayload newBlogPayload) {
        return this.blogsService.saveBlog(newBlogPayload);
    }

    @GetMapping
    public Page<Blog> findAllBlogs(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "author") String orderBy,
                                   @RequestParam(defaultValue = "asc") String sortCriteria) {

        return this.blogsService.findAllBlogs(page, size, orderBy, sortCriteria);
    }

    @GetMapping("/{blogId}")
    public Blog getBlogById(@PathVariable Long blogId) {
        return this.blogsService.findBlogById(blogId);
    }

    @PutMapping("/{blogId}")
    public Blog getBlogByIdAndUpdate(@PathVariable long blogId, @RequestBody NewBlogPayload payload) {
        return this.blogsService.findBlogByIdAndUpdate(blogId, payload);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void getBlogByIdAndDelete(@PathVariable long blogId) {
        this.blogsService.findBlogByIdAndDelete(blogId);
    }
    
}
