package andreapascarella.u5d8.controllers;

import andreapascarella.u5d8.entities.User;
import andreapascarella.u5d8.payloads.NewUserPayload;
import andreapascarella.u5d8.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody NewUserPayload payload) {
        return this.usersService.saveUser(payload);
    }

    @GetMapping
    public Page<User> findAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "surname") String orderBy,
                                   @RequestParam(defaultValue = "asc") String sortCriteria) {

        return this.usersService.findAllUsers(page, size, orderBy, sortCriteria);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return this.usersService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    public User getUserByIdAndUpdate(@PathVariable long userId, @RequestBody NewUserPayload payload) {
        return this.usersService.findByIdAndUpdate(userId, payload);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void getUserByIdAndDelete(@PathVariable long userId) {
        this.usersService.findByIdAndDelete(userId);
    }

}
