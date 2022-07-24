package ua.ivanyshen.passwordmanager.entities;

import java.util.Random;

public class PasswordGenerator {

    private final String[] chars = new String[] {"a", "b", "c", "d", "e", "f",
                                                    "g", "h", "i", "j", "k",
                                                    "l", "m", "n", "o", "p", "q",
                                                    "r", "s", "t", "u", "v",
                                                    "w", "x", "y", "z",
                                                    "A", "B", "C", "D", "E", "F",
                                                    "G", "H", "I", "J", "K",
                                                    "L", "M", "N", "O", "P",
                                                    "Q", "R", "S", "T", "U",
                                                    "V", "W", "X", "Y", "Z",
                                                    "0", "1", "2", "3", "4",
                                                    "5","6", "7", "8", "9",
                                                    "$", "#", "!", ".", ",", "!",
                                                    "?", ">", "<", "*", "+"};
    private int length = 15;
    private boolean specialChars = true;
    private int range = chars.length;

    public PasswordGenerator() {}

    public PasswordGenerator(int length, boolean specialChars) {
        this.length = length;
        this.specialChars = specialChars;
    }

    public String generate() {
        StringBuilder passwordBuilder = new StringBuilder();
        Random r = new Random();

        if(specialChars)
            range = chars.length;
        else
            range = chars.length - 11;

        for(int i = 0; i < length; i++)
            passwordBuilder.append(chars[r.nextInt(0, range)]);

        return passwordBuilder.toString();
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setSpecialChars(boolean specialChars) {
        this.specialChars = specialChars;
    }

    public boolean isSpecialChars() {
        return specialChars;
    }
}
