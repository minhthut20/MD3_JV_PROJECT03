package app.config;

import java.util.regex.Pattern;

public class ValidateConfig {
    public static boolean validatePassword(String data) {
        final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{1,10}$";
        return Pattern.matches(PASSWORD_REGEX, data);
    }

    public static boolean validateEmail(String data) {
        final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(EMAIL_REGEX, data);
    }

    public static boolean validateName(String data) {
        final String NAME_REGEX = ".{1,40}";
        return Pattern.matches(NAME_REGEX, data);
    }

    public static boolean validateUsername(String data) {
        final String USERNAME_REGEX = "^\\S{1,30}";
        return Pattern.matches(USERNAME_REGEX, data);
    }


}
