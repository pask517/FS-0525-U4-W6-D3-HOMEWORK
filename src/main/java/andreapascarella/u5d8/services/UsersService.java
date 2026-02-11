package andreapascarella.u5d8.services;

import andreapascarella.u5d8.entities.User;
import andreapascarella.u5d8.exceptions.BadRequestException;
import andreapascarella.u5d8.exceptions.NotFoundException;
import andreapascarella.u5d8.payloads.NewUserPayload;
import andreapascarella.u5d8.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Page<User> findAllUsers(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.usersRepository.findAll(pageable);
    }

    public User saveUser(NewUserPayload payload) {

        this.usersRepository.findByEmail(payload.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getBirthDate());
        newUser.setAvatar("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());

        User savedUser = this.usersRepository.save(newUser);

        log.info("L'utente con id " + savedUser.getId() + " è stato salvato correttamente!");

        return savedUser;
    }

    public User findUserById(Long userId) {
        return this.usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByIdAndUpdate(Long userId, NewUserPayload payload) {

        this.usersRepository.findByEmail(payload.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User found = this.findUserById(userId);

        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setBirthDate(payload.getBirthDate());
        found.setAvatar("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());

        User modifiedUser = this.usersRepository.save(found);

        log.info("L'utente con id " + modifiedUser.getId() + " è stato modificato correttamente");

        return modifiedUser;
    }

    public void findByIdAndDelete(long userId) {
        User found = this.findUserById(userId);
        this.usersRepository.delete(found);
    }
}
