package movieBooking;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String username;
    private String password;
    private List<Ticket> bookings;

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;

        this.bookings = new ArrayList<>();
    }

    

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void bookTicket(Ticket ticket) {
        bookings.add(ticket);
    }

    public Ticket cancelRecentTicket() {
        if (!bookings.isEmpty()) {
            return bookings.remove(bookings.size() - 1);
        }
        return null;
    }

    public Ticket viewRecentBooking() {
        if (!bookings.isEmpty()) {
            return bookings.get(bookings.size() - 1);
        }
        return null;
    }

    public List<Ticket> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', username='" + username + "', password='" + password + "', email='" + email + "'}";
    }
}
