package ua.ivanyshen.passwordmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.User;
import ua.ivanyshen.passwordmanager.interactors.UserInteractor;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserInteractor interactor;
    @Autowired
    public UserController(UserInteractor interactor) {
        this.interactor = interactor;
    }

    @PostMapping(value = "/new",
            produces = "application/json",
            consumes = "application/json")
    public User newUser(@RequestBody User u) {
        return interactor.createUser(u);
    }

    @GetMapping(value = "/{id}"
            , produces = "application/json")
    public User getUser(@PathVariable String id) {
        return interactor.find(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code =
            HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        interactor.deleteUser(id);
    }

    @PutMapping(value = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public User updateUser(@PathVariable String id, @RequestBody User u) {
        return interactor.updateUser(id, u);
    }
}
