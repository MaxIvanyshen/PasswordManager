package ua.ivanyshen.passwordmanager.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.InvalidPropertiesFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Getter
@Setter
public class User {

    private String email;
    private String username;
    private String masterPassword;

    @SneakyThrows
    public void setEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = p.matcher(email);
        while(matcher.find())
            this.email = matcher.group();
        if(this.email == null)
            throw new InvalidPropertiesFormatException("Invalid Email");
    }
}
