package movieBooking;

public class Ticket {
    private User user;
    private Movie movie;
    private Theater theater;
    private Screen screen;
    private Seat seat;

    public Ticket(User user, Movie movie, Theater theater, Screen screen, Seat seat) {
        this.user = user;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public Screen getScreen() {
        return screen;
    }

    public Seat getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", movie=" + movie +
                ", theater=" + theater +
                ", screen=" + screen +
                ", seat=" + seat +
                '}';
    }
}
