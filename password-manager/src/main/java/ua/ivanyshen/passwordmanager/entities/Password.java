package ua.ivanyshen.passwordmanager.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

public class Password {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private ArrayList<String> notes;

    public Password() {
        this.id = IdGenerator.generate(10);
    }

    public Password(String url, String password) {
        setUrl(url);
        this.password = password;
        this.id = IdGenerator.generate(10);
    }

    @SneakyThrows
    public void setUrl(String url) {
        if(url.matches("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})"))
            this.url = url;
        else
            throw new InvalidPropertiesFormatException("Invalid URL");
    }
}
