package movieBooking;

public class Ticket {
    private User user;
    private Movie movie;
    private Theater theater;
    private Screen screen;
    private Seat seat;
    private ShowTime showTime;
    private double price;

    public Ticket(User user, Movie movie, Theater theater, Screen screen, Seat seat, ShowTime showTime, double price) {
        this.user = user;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.seat = seat;
        this.showTime = showTime;
        this.price = price;
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

    public ShowTime getShowTime() {
        return showTime;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", movie=" + movie +
                ", theater=" + theater +
                ", screen=" + screen +
                ", seat=" + seat +
                ", showTime=" + showTime +
                ", price=" + price +
                '}';
    }
}
