package ua.ivanyshen.passwordmanager.entities;

import java.util.Random;

public class IdGenerator {


    public static String generate(int length) {
        String CHARACTER_SET = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append(CHARACTER_SET.charAt(rnd.nextInt(CHARACTER_SET.length())));
        }

        return builder.toString();
    }
}
