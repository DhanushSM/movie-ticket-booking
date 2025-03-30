# Movie Ticket Booking System

## About the Project
The Movie Ticket Booking System is a Java-based application designed to facilitate the booking of movie tickets. This system leverages various data structures to efficiently manage and process booking information. The primary goal is to provide a seamless and user-friendly experience for users to book movie tickets.

## Features
- **User-friendly Console Interface:** Developed a console-based interface that allows users to easily book and cancel tickets.
- **Efficient Seat Allocation:** Ensures optimal seat allocation using arrays.
- **Priority Handling:** Handles priority reservations using priority queues.
- **Booking History Management:** Utilizes stacks to manage booking history, allowing users to undo their last booking action if needed.

## Data Structures Used
The project utilizes the following data structures:

1. **Arrays:** Arrays are used to store and manage the available seats in the theater. Each element in the array represents a seat, and its value indicates whether the seat is booked or available.

2. **Stack:** A stack is used to manage the user's history of booked tickets, enabling users to undo their last booking action if needed.

3. **Priority Queue:** A priority queue is employed to handle priority booking scenarios, such as VIP bookings. The queue ensures that higher-priority bookings are processed before regular bookings.

## Installation
To set up the project locally, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/DhanushSM/movie-ticket-booking.git
    ```
2. Navigate to the project directory:
    ```bash
    cd movie-ticket-booking
    ```
3. Compile the Java files:
    ```bash
    javac -d bin src/*.java
    ```
4. Run the application:
    ```bash
    java -cp bin Main
    ```

## Usage
- The application will prompt the user to select a movie and showtime.
- Users can view available seats and select the seats they want to book.
- The system will process the booking and update the seat availability accordingly.
- Users can view their booking history and undo their last booking if needed.

## Contributing
Contributions are welcome! To contribute to this project, follow these steps:

1. Fork the repository.
2. Create a new branch:
    ```bash
    git checkout -b feature-name
    ```
3. Make your changes and commit them:
    ```bash
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```bash
    git push origin feature-name
    ```
5. Open a pull request to the `main` branch.

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Contact Information
- **Repository Owner:** [DhanushSM](https://github.com/DhanushSM)
- **Email:** [your-email@example.com]
