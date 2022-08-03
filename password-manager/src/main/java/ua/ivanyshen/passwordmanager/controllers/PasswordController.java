package ua.ivanyshen.passwordmanager.controllers;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.passwordmanager.db.PasswordListRepository;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.interactors.PasswordInteractor;

@RestController
@RequestMapping("/api/v1/passwords")
public class PasswordController {

    private PasswordInteractor interactor;

    @Autowired
    public PasswordController(PasswordInteractor interactor) {
        this.interactor = interactor;
    }

    @GetMapping("/new/{length}")
    public String generatePassword(@PathVariable int length) {
        return interactor.generatePassword(length, true);
    }

    @PostMapping(value = "/new",
            consumes = "application/json",
            produces = "application/json")
    public Password createPassword(@RequestBody Password pw) {
        return interactor.createPassword(pw);
    }

    @GetMapping(value = "/{id}",
            produces = "application/json")
    public Password getPassword(@PathVariable String id) {
        return interactor.find(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code =
            HttpStatus.NO_CONTENT)
    public void deletePassword(@PathVariable String id) {
        interactor.deletePassword(id);
    }

    @PutMapping(value = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public Password updatePassword(@PathVariable String id,
                                   @RequestBody Password pw) {
        return interactor.updatePassword(id, pw);
    }
}
