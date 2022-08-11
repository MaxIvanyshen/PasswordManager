package ua.ivanyshen.passwordmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.User;
import ua.ivanyshen.passwordmanager.interactors.UserInteractor;

import java.net.http.HttpResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserInteractor interactor;

    @Autowired
    private Environment env;

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

    @GetMapping(value = "/{id}/passwords",
            produces = "application/json")
    public ArrayList<String> getUserPasswords(@PathVariable String id) {
        return interactor.find(id).getPasswordsList();
    }

    @PostMapping(value = "/{userId}/passwords/{passwordId}")
    public ResponseEntity<ArrayList<String>> addPassword(@PathVariable String userId, @PathVariable String passwordId) {
        if(passwordIdIsCorrect(passwordId))
            return new ResponseEntity<>(interactor.addUserPassword(userId, passwordId), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private boolean passwordIdIsCorrect(String passwordId) {
        RestTemplate restTemplate = new RestTemplate();
        Password found = restTemplate.getForObject("http://localhost:8080/api/v1/passwords/{id}",
                Password.class, passwordId);
        if(found != null)
            return true;
        return false;
    }
}
