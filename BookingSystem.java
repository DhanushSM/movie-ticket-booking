package movieBooking;

import java.util.*;

public class BookingSystem {
    private List<Movie> movies;
    private List<Theater> theaters;
    private List<User> users;
    private PriorityQueue<WaitingTicket> waitingList;
    private Stack<Ticket> cancellationStack;
    private Scanner scanner;
    private Admin admin;
    private User currentUser;

    public BookingSystem() {
        movies = new ArrayList<>();
        theaters = new ArrayList<>();
        users = new ArrayList<>();
        waitingList = new PriorityQueue<>(Comparator.comparing(WaitingTicket::getBookingTime));
        cancellationStack = new Stack<>();
        scanner = new Scanner(System.in);
        admin = new Admin("dhanu", "dhanu123"); // default admin credentials
    }

    public void launch() {
        System.out.println("Welcome to the Movie Ticket Booking System!");
        while (true) {
            System.out.println("\n1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. User Registration");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    if (adminLogin()) {
                        adminMenu();
                    } else {
                        System.out.println("Invalid admin credentials, please try again.");
                    }
                    break;
                case 2:
                    if (userLogin()) {
                        userMenu();
                    } else {
                        System.out.println("Invalid user credentials, please try again.");
                    }
                    break;
                case 3:
                    registerUser();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private boolean adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        return admin.login(username, password);
    }

    private boolean userLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    private void registerUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                return;
            }
        }

        User newUser = new User(name, email, username, password);
        users.add(newUser);
        System.out.println("User registered: " + newUser);
    }

    private void adminMenu() {
        while (admin.isLoggedIn()) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Movie");
            System.out.println("2. Add Theater");
            System.out.println("3. Process Cancellation Queue");
            System.out.println("4. Show Waiting List");
            System.out.println("5. Allocate Tickets from Waiting List");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    addTheater();
                    break;
                case 3:
                    processCancellationQueue();
                    break;
                case 4:
                    showWaitingList();
                    break;
                case 5:
                    allocateTicketsFromWaitingList();
                    break;
                case 6:
                    admin.logout();
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void userMenu() {
        while (currentUser != null) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Recent Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Search Movie");
            System.out.println("6. Filter");
            System.out.println("7. sortby Genere");
            System.out.println("8. sortby location");
            System.out.println("9. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    cancelTicket();
                    break;
                case 3:
                    viewRecentBooking();
                    break;
                case 4:
                    viewAllBookings();
                    break;
                case 5:
                    searchMovie();
                    break;
                case 6:
                    searchMoviesByTitleGenreAndRating();
                    break;
                case 7:
                    sortMoviesByGenreDurationAndRating();
                    break;
                case 8:
                    sortTheatersByLocationScreensAndName();

                    break;
                case 9:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void addMovie() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter language: ");
        String language = scanner.nextLine();
        System.out.print("Enter rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        Movie movie = new Movie(title, genre, duration, language, rating);
        movies.add(movie);
        System.out.println("Movie added: " + movie);
    }

    private void addTheater() {
        System.out.print("Enter theater name: ");
        String name = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        Theater theater = new Theater(name, location);
        theaters.add(theater);
        System.out.println("Theater added: " + theater);

        // Add a default screen to the new theater
        Screen screen = new Screen(1, 10, 10);
        theater.addScreen(screen);
        System.out.println("Screen added: " + screen);
    }

    private void bookTicket() {
        if (currentUser == null) {
            return;
        }

        Movie movie = selectMovie();
        if (movie == null) {
            return;
        }

        Theater theater = selectTheater();
        if (theater == null) {
            return;
        }

        Screen screen = selectScreen(theater);
        if (screen == null) {
            return;
        }

        Seat seat = selectSeat(screen);
        if (seat == null) {
            return;
        }

        if (!seat.isBooked()) {
            Ticket ticket = new Ticket(currentUser, movie, theater, screen, seat);
            currentUser.bookTicket(ticket);
            seat.setBooked(true);
            System.out.println("Booked ticket: " + ticket);
        } else {
            WaitingTicket waitingTicket = new WaitingTicket(currentUser, movie, theater, screen, seat, new Date());
            waitingList.add(waitingTicket);
            System.out.println("Seat is already booked. Added to waiting list: " + waitingTicket);
        }
    }

    private void cancelTicket() {
        if (currentUser != null) {
            Ticket ticket = currentUser.cancelRecentTicket();
            if (ticket != null) {
                cancellationStack.push(ticket);
                ticket.getSeat().setBooked(false);
                System.out.println("Canceled ticket: " + ticket);
                System.out.println("Ticket added to cancellation stack.");
            } else {
                System.out.println("No recent ticket to cancel.");
            }
        }
    }

    private void viewRecentBooking() {
        if (currentUser != null) {
            Ticket recentTicket = currentUser.viewRecentBooking();
            if (recentTicket != null) {
                System.out.println("Recent booking: " + recentTicket);
            } else {
                System.out.println("No recent bookings found.");
            }
        }
    }

    private void viewAllBookings() {
        if (currentUser != null) {
            List<Ticket> allBookings = currentUser.getAllBookings();
            if (!allBookings.isEmpty()) {
                System.out.println("All bookings: " + allBookings);
            } else {
                System.out.println("No bookings found.");
            }
        }
    }

    private void processCancellationQueue() {
        System.out.println("Processing cancellation stack...");
        while (!cancellationStack.isEmpty()) {
            Ticket ticket = cancellationStack.pop();
            System.out.println("Processed cancellation for ticket: " + ticket);
        }
    }

    private void showWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("No tickets in the waiting list.");
        } else {
            System.out.println("Waiting list:");
            for (WaitingTicket waitingTicket : waitingList) {
                System.out.println(waitingTicket);
            }
        }
    }

    private void allocateTicketsFromWaitingList() {
        while (!waitingList.isEmpty()) {
            WaitingTicket waitingTicket = waitingList.poll();
            System.out.println("Allocating ticket from waiting list: " + waitingTicket);

            // Assuming seats are now available
            Seat seat = waitingTicket.getSeat();
            if (!seat.isBooked()) {
                Ticket ticket = new Ticket(waitingTicket.getUser(), waitingTicket.getMovie(),
                        waitingTicket.getTheater(), waitingTicket.getScreen(), seat);
                waitingTicket.getUser().bookTicket(ticket);
                seat.setBooked(true);
                System.out.println("Allocated ticket: " + ticket);
            } else {
                System.out.println("Seat is still booked. Cannot allocate ticket.");
            }
        }
    }

    private Movie selectMovie() {
        System.out.println("Available movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
        System.out.print("Select a movie: ");
        int movieIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline
        if (movieIndex >= 0 && movieIndex < movies.size()) {
            return movies.get(movieIndex);
        } else {
            System.out.println("Invalid movie selection.");
            return null;
        }
    }

    private Theater selectTheater() {
        System.out.println("Available theaters:");
        for (int i = 0; i < theaters.size(); i++) {
            System.out.println((i + 1) + ". " + theaters.get(i));
        }
        System.out.print("Select a theater: ");
        int theaterIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline
        if (theaterIndex >= 0 && theaterIndex < theaters.size()) {
            return theaters.get(theaterIndex);
        } else {
            System.out.println("Invalid theater selection.");
            return null;
        }
    }

    private Screen selectScreen(Theater theater) {
        System.out.println("Available screens:"+ theater.getName() + ":");
        List<Screen> screens = theater.getScreens();
        for (int i = 0; i < screens.size(); i++) {
            System.out.println((i + 1) + ". " + screens.get(i));
        }
        System.out.print("Select a screen: ");
        int screenIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline
        if (screenIndex >= 0 && screenIndex < screens.size()) {
            return screens.get(screenIndex);
        } else {
            System.out.println("Invalid screen selection.");
            return null;
        }
    }
    private Seat selectSeat(Screen screen) {
        System.out.println("Select a seat:");
        Seat[][] seats = screen.getSeats();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print("(" + (i + 1) + "," + (j + 1) + ") ");
            }
            System.out.println();
        }
        System.out.println("Seat Layout:");
        screen.displaySeats();

        System.out.print("Enter row number: ");
        int row = scanner.nextInt() - 1;
        System.out.print("Enter column number: ");
        int column = scanner.nextInt() - 1;
        scanner.nextLine();

        if (row < 0 || row >= seats.length || column < 0 || column >= seats[row].length) {
            System.out.println("Invalid seat selection.");
            return null;
        }

        return seats[row][column];
    }


    private void searchMovie() {
        System.out.print("Enter movie title to search: ");
        String title = scanner.nextLine();
        List<Movie> foundMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundMovies.add(movie);
            }
        }
        if (foundMovies.isEmpty()) {
            System.out.println("No movies found with the title: " + title);
        } else {
            System.out.println("Found movies:");
            for (Movie movie : foundMovies) {
                System.out.println(movie);
            }
        }
    }

    private void searchTheater() {
        System.out.print("Enter theater name to search: ");
        String name = scanner.nextLine();
        List<Theater> foundTheaters = new ArrayList<>();
        for (Theater theater : theaters) {
            if (theater.getName().toLowerCase().contains(name.toLowerCase())) {
                foundTheaters.add(theater);
            }
        }
        if (foundTheaters.isEmpty()) {
            System.out.println("No theaters found with the name: " + name);
        } else {
            System.out.println("Found theaters:");
            for (Theater theater : foundTheaters) {
                System.out.println(theater);
            }
        }
    }
    public void sortMoviesByLanguageDurationAndRating() {
        if (movies.isEmpty()) {
            System.out.println("No movies available to sort.");
            return;
        }

        System.out.print("Enter language to filter: ");
        String language = scanner.nextLine();

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getLanguage().equalsIgnoreCase(language)) {
                filteredMovies.add(movie);
            }
        }

        filteredMovies.sort(Comparator.comparingInt(Movie::getDuration)
                .thenComparingDouble(Movie::getRating).reversed());
        System.out.println("Movies sorted by duration and rating within the language \"" + language + "\": " + filteredMovies);
    }
    public void sortMoviesByGenreDurationAndRating() {
        if (movies.isEmpty()) {
            System.out.println("No movies available to sort.");
            return;
        }

        System.out.print("Enter genre to filter: ");
        String genre = scanner.nextLine();

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                filteredMovies.add(movie);
            }
        }

        filteredMovies.sort(Comparator.comparingInt(Movie::getDuration)
                .thenComparingDouble(Movie::getRating).reversed());
        System.out.println("Movies sorted by duration and rating within the genre \"" + genre + "\": " + filteredMovies);
    }
    public void searchMoviesByTitleGenreAndRating() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter minimum rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        List<Movie> results = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title) &&
                    movie.getGenre().equalsIgnoreCase(genre) &&
                    movie.getRating() >= rating) {
                results.add(movie);
            }
        }

        if (!results.isEmpty()) {
            System.out.println("Found movies: " + results);
        } else {
            System.out.println("No movies found with the given title, genre, and rating.");
        }
    }
    public void sortTheatersByLocationScreensAndName() {
        if (theaters.isEmpty()) {
            System.out.println("No theaters available to sort.");
            return;
        }

        System.out.print("Enter location to filter: ");
        String location = scanner.nextLine();

        List<Theater> filteredTheaters = new ArrayList<>();
        for (Theater theater : theaters) {
            if (theater.getLocation().equalsIgnoreCase(location)) {
                filteredTheaters.add(theater);
            }
        }

        filteredTheaters.sort(Comparator.comparingInt((Theater theater) -> theater.getScreens().size())
                .thenComparing(Theater::getName));
        System.out.println("Theaters sorted by number of screens and name within the location \"" + location + "\": " + filteredTheaters);
    }
}
