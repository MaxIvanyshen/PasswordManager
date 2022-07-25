package ua.ivanyshen.passwordmanager.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

@Data
@Getter
@Setter
public class Password {
    private String url;
    private String password;
    private ArrayList<String> notes;

    @SneakyThrows
    public void setUrl(String url) {
        if(url.matches("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})"))
            this.url = url;
        else
            throw new InvalidPropertiesFormatException("Invalid URL");
    }
}
