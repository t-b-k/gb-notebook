package notebook.controller;

import notebook.model.User;
import notebook.repository.GBRepository;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final GBRepository<User, Long> repository;

    public UserController(GBRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }
    public List<User> readAllUsers() {
        return repository.findAll();
    }

    public void updateUser(Long uid, User userToUpdate) {
        userToUpdate.setId(uid);
        System.out.printf("Changed ID of new user to %d\n", uid);
        repository.update(uid, userToUpdate);
        System.out.println("Updated repository with method repository.update");

    }
    public void deleteUser(Long uid) {
        repository.delete(uid);
        System.out.printf("\nDeleted user with id = %d\n", uid);
    }
}