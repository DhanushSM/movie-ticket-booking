package movieBooking;

public class ShowTime {
    private static int nextId = 1;

    private final int id;
    private final Movie movie;
    private final Theater theater;
    private final Screen screen;
    private final String startTime;
    private final double basePrice;

    public ShowTime(Movie movie, Theater theater, Screen screen, String startTime, double basePrice) {
        this.id = nextId++;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        this.basePrice = basePrice;
    }

    public int getId() {
        return id;
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

    public String getStartTime() {
        return startTime;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "id=" + id +
                ", movie=" + movie.getTitle() +
                ", theater=" + theater.getName() +
                ", screen=" + screen.getScreenNumber() +
                ", startTime='" + startTime + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
