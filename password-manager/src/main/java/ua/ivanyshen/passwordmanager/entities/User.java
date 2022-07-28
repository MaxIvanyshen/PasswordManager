package ua.ivanyshen.passwordmanager.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.InvalidPropertiesFormatException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Getter
@Setter
public class User {

    private String id;
    private String email;
    private String username;
    private String masterPassword;

    public User() {
        this.id = IdGenerator.generate(15);
    }

    public User(String email, String username, String masterPassword) {
        this.id = IdGenerator.generate(15);
        this.email = email;
        this.username = username;
        this.masterPassword = masterPassword;
    }

    @SneakyThrows
    public void setEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = p.matcher(email);
        while(matcher.find())
            this.email = matcher.group();
        if(this.email == null)
            throw new InvalidPropertiesFormatException("Invalid Email");
    }

    @Override
    public boolean equals(Object o) {
        User other = (User) o;
        if(this.id.equals(other.getId()) &&
                this.email.equals(other.getEmail()) &&
                this.username.equals(other.getUsername()) &&
                this.masterPassword.equals(other.getMasterPassword()))
            return true;
        return false;
    }
}
