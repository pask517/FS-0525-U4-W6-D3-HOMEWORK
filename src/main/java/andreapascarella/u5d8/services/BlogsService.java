package andreapascarella.u5d8.services;

import andreapascarella.u5d8.entities.Blog;
import andreapascarella.u5d8.entities.User;
import andreapascarella.u5d8.exceptions.NotFoundException;
import andreapascarella.u5d8.payloads.NewBlogPayload;
import andreapascarella.u5d8.repositories.BlogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BlogsService {

    private final UsersService usersService;

    private final BlogsRepository blogsRepository;

    @Autowired
    public BlogsService(UsersService usersService, BlogsRepository blogsRepository) {
        this.usersService = usersService;
        this.blogsRepository = blogsRepository;
    }

    public Page<Blog> findAllBlogs(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.blogsRepository.findAll(pageable);
    }

    public Blog saveBlog(NewBlogPayload payload) {

        User author = usersService.findUserById(payload.getAuthorId());

        Blog newBlog = new Blog(payload.getBlogCategory(), payload.getCover(), payload.getTitle(), payload.getContent(), payload.getReadingTime(), author);

        Blog savedBlog = this.blogsRepository.save(newBlog);

        log.info("Il blog con id " + savedBlog.getId() + " è stato salvato correttamente!");

        return savedBlog;
    }

    public Blog findBlogById(long blogId) {
        return this.blogsRepository.findById(blogId)
                .orElseThrow(() -> new NotFoundException(blogId));
    }

    public Blog findBlogByIdAndUpdate(Long blogId, NewBlogPayload payload) {

        Blog found = this.findBlogById(blogId);

        found.setCover(payload.getCover());
        found.setBlogCategory(payload.getBlogCategory());
        found.setTitle(payload.getTitle());
        found.setContent(payload.getContent());
        found.setReadingTime(payload.getReadingTime());

        Blog modifiedBlog = this.blogsRepository.save(found);

        log.info("Il blog con id " + modifiedBlog.getId() + " è stato modificato correttamente");

        return modifiedBlog;
    }

    public void findBlogByIdAndDelete(long blogId) {
        Blog found = this.findBlogById(blogId);
        this.blogsRepository.delete(found);
    }
}
