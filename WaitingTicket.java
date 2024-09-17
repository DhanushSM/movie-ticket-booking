package movieBooking;

import java.util.Date;

public class WaitingTicket {
    private User user;
    private Movie movie;
    private Theater theater;
    private Screen screen;
    private Seat seat;
    private Date bookingTime;

    public WaitingTicket(User user, Movie movie, Theater theater, Screen screen, Seat seat, Date bookingTime) {
        this.user = user;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.seat = seat;
        this.bookingTime = bookingTime;
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

    public Date getBookingTime() {
        return bookingTime;
    }

    @Override
    public String toString() {
        return "WaitingTicket{" +
                "user=" + user +
                ", movie=" + movie +
                ", theater=" + theater +
                ", screen=" + screen +
                ", seat=" + seat +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
