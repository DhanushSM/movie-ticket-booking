package movieBooking;

public class Movie {
    private String title;
    private String genre;
    private int duration;
    private String language;
    private double rating;

    public Movie(String title, String genre, int duration, String language, double rating) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.language = language;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getLanguage() {
        return language;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", language='" + language + '\'' +
                ", rating=" + rating +
                '}';
    }
}
