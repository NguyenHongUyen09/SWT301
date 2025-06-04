package uyen.example;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class AccountService {
    private Set<String> registeredEmails = new HashSet<>();

    public boolean isValidEmail(String email) {
        if (email == null) return false;
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() > 6;
    }

    public boolean isUsernameValid(String username) {
        return username != null && !username.trim().isEmpty();
    }

    public boolean isEmailUnique(String email) {
        return email != null && !registeredEmails.contains(email);
    }

    public boolean registerAccount(String username, String password, String email) {
        if (!isUsernameValid(username)) return false;
        if (!isValidPassword(password)) return false;
        if (!isValidEmail(email)) return false;
        if (!isEmailUnique(email)) return false;

        // Register user
        registeredEmails.add(email);
        return true;
    }

    public void reset() {
        registeredEmails.clear();
    }
}
