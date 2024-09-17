package movieBooking;

public class Seat {
    private int row;
    private int col;
    private boolean isBooked;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.isBooked = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
    public String display() {
        if (isBooked) {
            return "[" + row + "," + col + " (B)]"; // Seat display string for booked seat
        } else {
            return "[" + row + "," + col + "]"; // Seat display string for available seat
        }
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", col=" + col +
                ", isBooked=" + isBooked +
                '}';
    }
}
