package movieBooking;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenNumber;
    private int numRows;
    private int numCols;
    private Seat[][] seats;

    public Screen(int screenNumber, int rows, int columns) {
        this.screenNumber = screenNumber;
        this.numRows = rows;
        this.numCols = columns;
        seats = new Seat[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = new Seat(i + 1, j + 1); // Seat numbering starts from 1
            }
        }
    }

    public Seat[][] getSeats() {
        return seats;
    }
    public int getScreenNumber() {
        return screenNumber;
    }

    public Seat getSeat(String seatNumber) {
        String[] parts = seatNumber.split(",");
        int row = Integer.parseInt(parts[0]) - 1;
        int col = Integer.parseInt(parts[1]) - 1;
        if (row >= 0 && row < seats.length && col >= 0 && col < seats[row].length) {
            return seats[row][col];
        }
        return null;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (!seats[i][j].isBooked()) {
                    availableSeats.add(seats[i][j]);
                }
            }
        }
        return availableSeats;
    }

    public void displaySeats() {
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (seat.isBooked()) {
                    System.err.print(seat.display() + " "); // Display booked seat in red
                } else {
                    System.out.print(seat.display() + " "); // Display available seat in normal color
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Screen " + screenNumber + " (Rows: " + numRows + ", Columns: " + numCols + ")";
    }
}
