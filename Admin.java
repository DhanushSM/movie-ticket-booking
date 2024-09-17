package movieBooking;

import java.util.Scanner;

public class Admin {
    private String username;
    private String password;
    private boolean loggedIn;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
